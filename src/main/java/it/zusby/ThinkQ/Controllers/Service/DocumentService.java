package it.zusby.ThinkQ.Controllers.Service;

import dev.langchain4j.model.ollama.OllamaLanguageModel;
import dev.langchain4j.model.output.Response;
import it.zusby.ThinkQ.Mappers.DocumentModelMapper;
import it.zusby.ThinkQ.Types.Dto.DocumentCreateDTO;
import it.zusby.ThinkQ.Types.Dto.TestDocumentDTO;
import it.zusby.ThinkQ.Types.Model.DocumentModel;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class DocumentService {
    private final OllamaLanguageModel model;
    private final DocumentModelMapper mapper;
    public DocumentService(@Qualifier("documentModelMapperImpl") DocumentModelMapper mapper) {
        this.mapper = mapper;
        this.model = OllamaLanguageModel.builder()
                .baseUrl("http://localhost:11434")
                .modelName("gemma3:12b")
                .numCtx(4096)
                .timeout(Duration.ofMinutes(10))
                .build();
    }


    public TestDocumentDTO  generateQuestions(TestDocumentDTO doc){
        Response<String> output = model.generate(
                """
                Leggi il seguente testo e genera una domanda a scelta multipla con una sola risposta corretta.
                Usa esattamente questo formato:
           \s
                DOMANDA: Qual è la capitale d'Italia? \s
                RISPOSTA 1: Milano \s
                RISPOSTA 2: Roma \s
                RISPOSTA 3: Napoli \s
                RISPOSTA 4: Torino \s
                RISPOSTA CORRETTA: 2
           \s
                Ora il testo:
               \s""" + doc.getTextContent()
        );

        String[] lines = output.content().split("\n");
        if (lines.length >= 5) {
            doc.setQuestion(lines[0].replace("DOMANDA: ", "").trim());
            doc.setAnswer1(lines[1].replace("DOMANDA 1: ", "").trim());
            doc.setAnswer2(lines[2].replace("DOMANDA 2: ", "").trim());
            doc.setAnswer3(lines[3].replace("DOMANDA 3: ", "").trim());
            doc.setAnswer4(lines[4].replace("DOMANDA 4: ", "").trim());
            doc.setCorrectAnswer(lines[5].replace("RISPOSTA CORRETTA: ", "").trim());
        }
        return doc;
    }

    public void generateQuestions(DocumentCreateDTO doc) {
        if(doc.getContent() == null || doc.getContent().isEmpty()){
            throw new ServiceException("Contenuto del documento non valido");
        }
        if(doc.getTitle() == null || doc.getTitle().isEmpty()){
            throw new ServiceException("Contenuto del titolo non valido");
        }
        if(doc.getAppUser() == null || doc.getAppUser().isEmpty()){
            throw new ServiceException("App user non valido");
        }
        DocumentModel model = mapper.fromCreateDTO(doc);
        model.setCreatedAt(LocalDateTime.now());
    }
}
