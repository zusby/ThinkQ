package it.zusby.ThinkQ.Types.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class GeneratedQuestionModel extends AbstractModel{

    private String questionText;
    private String sourceText;

    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String correctAnswer;
    private QuestionFeedbackModel feedbacks;
    private UUID DocumentId;
}
