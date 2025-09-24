package it.zusby.ThinkQ.Controllers.Service;

import dev.langchain4j.model.ollama.OllamaLanguageModel;
import dev.langchain4j.model.output.Response;
import it.zusby.ThinkQ.Types.Model.AbstractModel;
import it.zusby.ThinkQ.Types.Model.DocumentModel;
import it.zusby.ThinkQ.Types.Model.GeneratedQuestionModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class OllamaServiceManager {
    private final OllamaLanguageModel model;

    public OllamaServiceManager(@Value("${spring.llm.url}") String url,
                                @Value("${spring.llm.modelName}") String modelName,
                                @Value("${spring.llm.timeoutInMinutes}") Integer timeoutInMinutes) {
        this.model = OllamaLanguageModel.builder()
                .baseUrl(url)
                .modelName(modelName)
                .numCtx(4096)
                .timeout(Duration.ofMinutes(timeoutInMinutes))
                .build();
    }

    /**
     * Genera una sola domanda e la trasforma in GeneratedQuestionModel.
     */
    public GeneratedQuestionModel generateSingleQuestion(String text, UUID documentId, AbstractModel callerModel) {
        Response<String> output = model.generate(
                """
                Leggi il seguente testo e genera una domanda a scelta multipla con una sola risposta corretta e la sorgente da cui hai preso la domanda, ovvero il pezzo di testo da cui l'hai presa.
                Usa esattamente questo formato:
           
                DOMANDA: Qual è la capitale d'Italia?
                RISPOSTA 1: Milano
                RISPOSTA 2: Roma
                RISPOSTA 3: Napoli
                RISPOSTA 4: Torino
                RISPOSTA CORRETTA: 2
                SORGENTE DOMANDA: Tra le varie capitali degli stati del mondo, Roma in Italia, ospita il Colosseo assieme a gran parte del patrimonio dell'UNESCO
           
                Ora il testo:
                """ + text
        );

        return parseQuestionBlock(output.content(), documentId, callerModel);
    }

    /**
     * genera n domande facendo n chiamate indipendenti.
     */
    public List<GeneratedQuestionModel> generateMultipleQuestionsOneByOne(String text, int n, UUID documentId,AbstractModel callerModel) {
        List<GeneratedQuestionModel> questions = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            questions.add(generateSingleQuestion(text, documentId, callerModel));
        }
        return questions;
    }

    /**
     * genera n domande in una sola chiamata.
     */
    public List<GeneratedQuestionModel> generateMultipleQuestionsInOneCall(String text, int n,  UUID documentId, AbstractModel callerModel) {
        String line = " Leggi il seguente testo e genera " + n + "domande a scelta multipla.";
        Response<String> output = model.generate(
                line+
                """
                Ogni domanda deve avere una sola risposta corretta e includere la sorgente da cui è stata estratta.
                Usa esattamente questo formato e ripetilo per ciascuna domanda:
                
                DOMANDA: Qual è la capitale d'Italia?
                RISPOSTA 1: Milano
                RISPOSTA 2: Roma
                RISPOSTA 3: Napoli
                RISPOSTA 4: Torino
                RISPOSTA CORRETTA: 2
                SORGENTE DOMANDA: Tra le varie capitali degli stati del mondo, Roma in Italia, ospita il Colosseo assieme a gran parte del patrimonio dell'UNESCO
                
                Ora il testo:
                """ + text
        );

        String[] rawQuestions = output.content().split("(?=DOMANDA:)");

        List<GeneratedQuestionModel> questions = new ArrayList<>();
        for (String q : rawQuestions) {
            if (!q.trim().isEmpty()) {
                questions.add(parseQuestionBlock(q, documentId, callerModel));
            }
        }

        return questions;
    }

    /**
     * Parser che trasforma un blocco di testo del modello in GeneratedQuestionModel.
     */
    private GeneratedQuestionModel parseQuestionBlock(String block,  UUID documentId,AbstractModel model) {
        GeneratedQuestionModel question = new GeneratedQuestionModel();

        question.setDocumentId(documentId);
        question.setAppUser(model.getAppUser());
        question.setCreatedAt(LocalDateTime.now());


        String[] lines = block.split("\n");
        List<String> options = new ArrayList<>();

        for (String line : lines) {
            line = line.trim();
            if (line.startsWith("DOMANDA:")) {
                question.setQuestionText(line.replace("DOMANDA:", "").trim());
            } else if (line.startsWith("RISPOSTA ")) {
                options.add(line.substring(line.indexOf(":") + 1).trim());
            } else if (line.startsWith("RISPOSTA CORRETTA:")) {
                question.setCorrectAnswer(line.replace("RISPOSTA CORRETTA:", "").trim());
            } else if (line.startsWith("SORGENTE DOMANDA:")) {
                question.setSourceText(line.replace("SORGENTE DOMANDA:", "").trim());
            }
        }
        question.setOption1(options.get(0));
        question.setOption2(options.get(1));
        question.setOption3(options.get(2));
        question.setOption4(options.get(3));



        return question;
    }
}
