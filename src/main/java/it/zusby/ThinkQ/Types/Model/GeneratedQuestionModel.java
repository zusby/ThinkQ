package it.zusby.ThinkQ.Types.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class GeneratedQuestionModel extends AbstractModel{

    private String questionText;
    private String sourceText;

    private List<String> options;
    private String correctAnswer;

    private DocumentModel document;
    private QuestionFeedbackModel feedbacks;
}
