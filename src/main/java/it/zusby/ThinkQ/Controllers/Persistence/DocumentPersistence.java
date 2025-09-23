package it.zusby.ThinkQ.Controllers.Persistence;

import it.zusby.ThinkQ.Controllers.Persistence.Repositories.DocumentRepository;
import it.zusby.ThinkQ.Mappers.DocumentEntityMapper;
import it.zusby.ThinkQ.Types.Model.DocumentModel;
import jakarta.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DocumentPersistence {
    private final DocumentRepository repository;
    private final DocumentEntityMapper mapper;
    private final DocumentEntityMapper documentEntityMapper;

    @Autowired
    public DocumentPersistence(DocumentRepository documentRepository, DocumentEntityMapper documentEntityMapper) {
        this.repository = documentRepository;
        this.mapper = documentEntityMapper;
        this.documentEntityMapper = documentEntityMapper;
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
        if(doc.isPresent()) {
            return mapper.fromEntityToModel(doc.get());
        }
        return null;
    }
}
