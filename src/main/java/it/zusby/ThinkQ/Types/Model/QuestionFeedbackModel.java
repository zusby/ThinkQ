package it.zusby.ThinkQ.Types.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class QuestionFeedbackModel  extends AbstractModel{
    private String comment;
    private String correctAnswer;
    private UUID questionId;
}
