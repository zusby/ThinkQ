package it.zusby.ThinkQ.Controllers.Persistence.Repositories;

import it.zusby.ThinkQ.Types.Entity.GeneratedQuestionEntity;
import it.zusby.ThinkQ.Types.Entity.QuestionFeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface QuestionFeedbackRepository  extends JpaRepository<QuestionFeedbackEntity, UUID> {

    QuestionFeedbackEntity getFirstByGeneratedQuestion_Id(UUID generatedQuestionId);
}
