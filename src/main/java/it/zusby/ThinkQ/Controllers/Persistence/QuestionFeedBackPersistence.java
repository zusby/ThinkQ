package it.zusby.ThinkQ.Controllers.Persistence;

import it.zusby.ThinkQ.Controllers.Persistence.Repositories.QuestionFeedbackRepository;
import it.zusby.ThinkQ.Controllers.Resource.QuestionFeedbackResource;
import it.zusby.ThinkQ.Mappers.QuestionFeedbackEntityMapper;
import it.zusby.ThinkQ.Types.Dto.QuestionFeedbackDTO;
import it.zusby.ThinkQ.Types.Entity.QuestionFeedbackEntity;
import it.zusby.ThinkQ.Types.Model.QuestionFeedbackModel;
import jakarta.persistence.PersistenceException;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class QuestionFeedBackPersistence {

    private final QuestionFeedbackRepository rep;
    private final QuestionFeedbackEntityMapper mapper;

    @Autowired
    public QuestionFeedBackPersistence(@Qualifier("questionFeedbackEntityMapperImpl") QuestionFeedbackEntityMapper mapper, QuestionFeedbackRepository repository) {
        this.rep = repository;
        this.mapper = mapper;
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
            var saved = rep.save(mapper.toEntity(model));
            return mapper.toModel(saved);
        } catch (PersistenceException e) {
            throw new PersistenceException(e.getMessage());
        }
    }
}
