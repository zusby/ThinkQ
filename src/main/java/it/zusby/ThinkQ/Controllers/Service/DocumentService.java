package it.zusby.ThinkQ.Controllers.Service;

import dev.langchain4j.model.ollama.OllamaLanguageModel;
import dev.langchain4j.model.output.Response;
import it.zusby.ThinkQ.Controllers.Persistence.DocumentPersistence;
import it.zusby.ThinkQ.Mappers.DocumentModelMapper;
import it.zusby.ThinkQ.Types.Dto.DocumentCreateDTO;
import it.zusby.ThinkQ.Types.Dto.DocumentDTO;
import it.zusby.ThinkQ.Types.Dto.TestDocumentDTO;
import it.zusby.ThinkQ.Types.Model.DocumentModel;
import it.zusby.ThinkQ.Types.Model.GeneratedQuestionModel;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class DocumentService {
    private final DocumentModelMapper mapper;
    private final OllamaServiceManager llmService;
    private final DocumentPersistence persistence;

    @Autowired
    public DocumentService(@Qualifier("documentModelMapperImpl") DocumentModelMapper mapper, OllamaServiceManager llmService, DocumentPersistence persistence) {
        this.mapper = mapper;
        this.llmService = llmService;
        this.persistence = persistence;
    }


    public DocumentDTO generateQuestions(DocumentCreateDTO doc) {
        validate(doc);
        DocumentModel model = mapper.fromCreateDTO(doc);
        model.setCreatedAt(LocalDateTime.now());
        var saved = persistence.save(model);

        var question = llmService.generateSingleQuestion(doc.getContent(),saved.getId());
        saved.setQuestions(List.of(question));

        return mapper.fromModelToDto(persistence.save(saved));

    }

    public DocumentDTO generateQuestions(DocumentCreateDTO doc, int questions) {
        validate(doc);
        DocumentModel model = mapper.fromCreateDTO(doc);
        model.setCreatedAt(LocalDateTime.now());
        //TODO aggiungere logica sul prompt per LLM

        return null;
    }


    public DocumentDTO getDocument(String id) {
        return null;
    }

    public boolean delete(String id) {
        return false;
    }

    private void validate(DocumentCreateDTO doc) {
        String error = "";
        var flag = false;
        if (doc.getContent() == null || doc.getContent().isEmpty()) {
            flag = true;
            error += "Contenuto del documento non valido";
        }
        if (doc.getTitle() == null || doc.getTitle().isEmpty()) {
            flag = true;
            error += " Titolo non valido";
        }
        if (doc.getAppUser() == null || doc.getAppUser().isEmpty()) {
            flag = true;
            error += " AppUser non valido";
        }
        if (flag) {
            throw new ServiceException(error);
        }
    }
}
