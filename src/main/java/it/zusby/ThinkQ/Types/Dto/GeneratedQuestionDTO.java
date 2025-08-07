package it.zusby.ThinkQ.Types.Dto;

import it.zusby.ThinkQ.Types.Model.AbstractModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
@EqualsAndHashCode(callSuper = true)
@Data
public class GeneratedQuestionDTO extends AbstractModel {
    private String questionText;
    private String sourceText;

    private List<String> options;
    private String correctAnswer;

    private DocumentDTO document;
    private QuestionFeedbackDTO feedbacks;
}
