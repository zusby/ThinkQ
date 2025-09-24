package it.zusby.ThinkQ.Controllers.Persistence;

import it.zusby.ThinkQ.Controllers.Persistence.Repositories.DocumentRepository;
import it.zusby.ThinkQ.Controllers.Persistence.Repositories.GeneratedQuestionRepository;
import it.zusby.ThinkQ.Mappers.DocumentEntityMapper;
import it.zusby.ThinkQ.Types.Dto.GeneratedQuestionDTO;
import it.zusby.ThinkQ.Types.Model.DocumentModel;
import jakarta.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DocumentPersistence {
    private final DocumentRepository repository;
    private final GeneratedQuestionRepository generatedQuestionRepository;
    private final DocumentEntityMapper mapper;
    private final DocumentEntityMapper documentEntityMapper;

    @Autowired
    public DocumentPersistence(DocumentRepository documentRepository, DocumentEntityMapper documentEntityMapper, GeneratedQuestionRepository gqr) {
        this.repository = documentRepository;
        this.mapper = documentEntityMapper;
        this.documentEntityMapper = documentEntityMapper;
        generatedQuestionRepository = gqr;
    }

    public DocumentModel save(DocumentModel model) {

        var entity = documentEntityMapper.fromModelToEntity(model);
        if (entity.getQuestions() != null) {
            for (var question : entity.getQuestions()) {
                question.setDocument(entity);
            }
        }
        return mapper.fromEntityToModel(repository.save(entity));
    }

    public DocumentModel findById(String id) {

        var doc = this.repository.findById(UUID.fromString(id));
        if (doc.isPresent()) {
            var model = mapper.fromEntityToModel(doc.get());
            for (var question : model.getQuestions()) {
                question.setDocumentId(doc.get().getId());
            }
            return model;
        }
        return null;
    }

    public boolean delete(String id) {
        var entity = findById(id);
        if (entity != null) {
            this.repository.deleteById(entity.getId());
            return true;
        }
        return false;
    }

    public List<GeneratedQuestionDTO> findQuestionsByDocumentId(String id) {
        return this.generatedQuestionRepository.findDistinctByDocument_Id(UUID.fromString(id));
    }
}

