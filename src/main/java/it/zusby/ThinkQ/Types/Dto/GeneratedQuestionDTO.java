package it.zusby.ThinkQ.Types.Dto;

import it.zusby.ThinkQ.Types.Model.AbstractModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class GeneratedQuestionDTO extends AbstractModel {
    private String questionText;
    private String sourceText;

    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String correctAnswer;

    private UUID documentId;
    private QuestionFeedbackDTO feedbacks;
}
