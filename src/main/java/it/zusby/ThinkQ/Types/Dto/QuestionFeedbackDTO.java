package it.zusby.ThinkQ.Types.Dto;

import it.zusby.ThinkQ.Types.Model.GeneratedQuestionModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;


@EqualsAndHashCode(callSuper = true)
@Data
public class QuestionFeedbackDTO extends AbstractDTO {

    private String comment;
    private boolean isCorrect;
    private LocalDateTime submittedAt;
    private GeneratedQuestionModel question;

}