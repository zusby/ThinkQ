package it.zusby.ThinkQ.Controllers.Service;

import it.zusby.ThinkQ.Controllers.Persistence.GeneratedQuestionPersistence;
import it.zusby.ThinkQ.Controllers.Persistence.QuestionFeedBackPersistence;
import it.zusby.ThinkQ.Mappers.QuestionFeedbackModelMapper;
import it.zusby.ThinkQ.Types.Dto.QuestionFeedbackDTO;
import it.zusby.ThinkQ.Types.Model.QuestionFeedbackModel;
import jakarta.validation.constraints.Null;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class QuestionFeedbackService {

    private final QuestionFeedBackPersistence qp;
    private final QuestionFeedbackModelMapper mapper;
    private final GeneratedQuestionPersistence questionPersistence;
    @Autowired
    public QuestionFeedbackService(@Qualifier("questionFeedbackModelMapperImpl") QuestionFeedbackModelMapper mapper, QuestionFeedBackPersistence persistence, GeneratedQuestionPersistence questionPersistence, GeneratedQuestionPersistence questionPersistence1){
        this.qp = persistence;
        this.mapper = mapper;
        this.questionPersistence = questionPersistence;
    }

    public QuestionFeedbackDTO getFeedbackById(UUID id) {
        if(id == null)
            throw new NullPointerException("id is null");

        try{
            return mapper.toDTO(this.qp.getFeedbackById(id));
        }catch (ServiceException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public QuestionFeedbackDTO createFeedback(QuestionFeedbackDTO dto) {
        var model = mapper.toModel(dto);
        if(model == null || model.getQuestionId() == null){
            throw new ServiceException("Feedback must have a question id");
        }

        model.setCreatedAt(LocalDateTime.now());
        try{
            var question = questionPersistence.getGeneratedQuestion(model.getQuestionId());
            if(!model.getCorrectAnswer().equals(question.getCorrectAnswer())){
                question.setCorrectAnswer(model.getCorrectAnswer());
                questionPersistence.updateCorrectAnswer(model.getCorrectAnswer(),question.getId());
            }
            return mapper.toDTO(qp.createFeedback(model));
        }catch (ServiceException e){
            throw new ServiceException(e.getMessage());
        }

    }
}
