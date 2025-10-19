package it.zusby.ThinkQ.Controllers.Persistence;

import it.zusby.ThinkQ.Controllers.Persistence.Repositories.GeneratedQuestionRepository;
import it.zusby.ThinkQ.Mappers.GeneratedQuestionEntityMapper;
import it.zusby.ThinkQ.Types.Dto.GeneratedQuestionDTO;
import it.zusby.ThinkQ.Types.Entity.GeneratedQuestionEntity;
import it.zusby.ThinkQ.Types.Entity.QuestionFeedbackEntity;
import it.zusby.ThinkQ.Types.Model.GeneratedQuestionModel;
import it.zusby.ThinkQ.Types.Model.QuestionFeedbackModel;
import jakarta.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GeneratedQuestionPersistence {

    private final GeneratedQuestionRepository repository;
    private final GeneratedQuestionEntityMapper mapper;
    @Autowired
    public GeneratedQuestionPersistence(@Qualifier("generatedQuestionEntityMapperImpl") GeneratedQuestionEntityMapper mapper, GeneratedQuestionRepository repository) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<GeneratedQuestionDTO> findQuestionsByDocumentId(String id) {
        return this.repository.findDistinctByDocument_Id(UUID.fromString(id));
    }

    public GeneratedQuestionModel getGeneratedQuestion(UUID questionId) {

        try{
            Optional<GeneratedQuestionEntity> entity = this.repository.findById(questionId);
                                          if(entity.isEmpty()){
                throw new PersistenceException("Question not found");
            }
            return mapper.toModel(entity.get());
        }
        catch (PersistenceException e){
            throw new PersistenceException(e.getMessage());
        }
    }

    public void save(GeneratedQuestionModel question) {
        try{
            var toSave = repository.save(
                    mapper.toEntity(question));
        }catch (PersistenceException e){
            throw new PersistenceException(e.getMessage());
        }
    }

    public void updateCorrectAnswer(String correctAnswer, UUID id) {
        repository.updateCorrectAnswerById(correctAnswer, id);
    }

}
