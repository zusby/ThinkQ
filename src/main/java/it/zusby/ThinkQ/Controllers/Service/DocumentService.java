package it.zusby.ThinkQ.Controllers.Service;

import it.zusby.ThinkQ.Controllers.Persistence.DocumentPersistence;
import it.zusby.ThinkQ.Mappers.DocumentModelMapper;
import it.zusby.ThinkQ.Types.Dto.DocumentCreateDTO;
import it.zusby.ThinkQ.Types.Dto.DocumentDTO;
import it.zusby.ThinkQ.Types.Dto.GeneratedQuestionDTO;
import it.zusby.ThinkQ.Types.Model.DocumentModel;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

    public DocumentDTO generateNewQuestions(String id) {
        var model = this.persistence.findById(id);
        if(model!=null){
            var question = llmService.generateSingleQuestion(model.getContent(),model.getId(),model);
            model.setQuestions(List.of(question));
            return mapper.fromModelToDto(persistence.save(model));
        }
        throw new ServiceException("No question found with id " + id);
    }
    public DocumentDTO generateQuestions(DocumentCreateDTO doc) {
        validate(doc);
        DocumentModel model = mapper.fromCreateDTO(doc);
        model.setCreatedAt(LocalDateTime.now());
        var savedModel = persistence.save(model);

        var question = llmService.generateSingleQuestion(doc.getContent(),savedModel.getId(),savedModel);
        savedModel.setQuestions(List.of(question));

        return mapper.fromModelToDto(persistence.save(savedModel));

    }

    public DocumentDTO generateQuestions(DocumentCreateDTO doc, int questions) {
        validate(doc);
        DocumentModel model = mapper.fromCreateDTO(doc);
        model.setCreatedAt(LocalDateTime.now());
        //TODO aggiungere logica sul prompt per LLM

        return null;
    }


    public DocumentDTO getDocument(String id) {
        return mapper.fromModelToDto(this.persistence.findById(id));
    }

    public boolean delete(String id) {
        return this.persistence.delete(id);
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
            error += " appUser non valido";
        }
        if (flag) {
            throw new ServiceException(error);
        }
    }


    public List<GeneratedQuestionDTO> getQuestionsByDocumentId(String id) {
        return this.persistence.findQuestionsByDocumentId(id);
    }
}
