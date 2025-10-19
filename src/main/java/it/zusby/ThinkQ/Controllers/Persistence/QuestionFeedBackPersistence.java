package it.zusby.ThinkQ.Controllers.Persistence;

import it.zusby.ThinkQ.Controllers.Persistence.Repositories.GeneratedQuestionRepository;
import it.zusby.ThinkQ.Controllers.Persistence.Repositories.QuestionFeedbackRepository;
import it.zusby.ThinkQ.Mappers.GeneratedQuestionEntityMapper;
import it.zusby.ThinkQ.Mappers.QuestionFeedbackEntityMapper;
import it.zusby.ThinkQ.Types.Entity.GeneratedQuestionEntity;
import it.zusby.ThinkQ.Types.Model.GeneratedQuestionModel;
import it.zusby.ThinkQ.Types.Model.QuestionFeedbackModel;
import jakarta.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class QuestionFeedBackPersistence {

    private final QuestionFeedbackRepository rep;
    private final QuestionFeedbackEntityMapper mapper;
    private final GeneratedQuestionRepository questionRepository;
    private final GeneratedQuestionEntityMapper questionMapper;

    @Autowired
    public QuestionFeedBackPersistence(@Qualifier("questionFeedbackEntityMapperImpl") QuestionFeedbackEntityMapper mapper,
                                       @Qualifier("generatedQuestionEntityMapperImpl") GeneratedQuestionEntityMapper questionMapper,
                                       QuestionFeedbackRepository repository,
                                       GeneratedQuestionRepository questionRepository) {
        this.rep = repository;
        this.mapper = mapper;
        this.questionRepository = questionRepository;
        this.questionMapper = questionMapper;
    }

    public QuestionFeedbackModel getFeedbackById(UUID id) {
        return mapper.toModel(
                this.rep.findById(id)
                        .orElseThrow(
                                () -> new PersistenceException("Feedback not found")));
    }

    public QuestionFeedbackModel createFeedback(QuestionFeedbackModel model) {
        if (rep.getFirstByGeneratedQuestion_Id(model.getQuestionId()) != null) {
            throw new PersistenceException("Feedback already sent");
        }

        try {
            var entity = mapper.toEntity(model);
            entity.setGeneratedQuestion(questionRepository.findById(
                    model.getQuestionId())
                    .orElseThrow(
                            () -> new PersistenceException("Question not found")));
            var saved = rep.save(entity);
            return mapper.toModel(saved);
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }


}
