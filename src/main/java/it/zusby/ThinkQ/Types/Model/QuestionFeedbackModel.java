package it.zusby.ThinkQ.Types.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class QuestionFeedbackModel  extends AbstractModel{
    private String comment;
    private boolean isCorrect;
    private LocalDateTime submittedAt;
    private GeneratedQuestionModel question;
}
