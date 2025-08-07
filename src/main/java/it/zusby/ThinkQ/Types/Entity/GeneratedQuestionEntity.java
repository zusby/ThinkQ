package it.zusby.ThinkQ.Types.Entity;

import jakarta.persistence.Entity;
import lombok.*;
import java.util.List;


@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class GeneratedQuestionEntity extends AbstractEntity {

    private String questionText;
    private String sourceText;
    private List<String> options;
    private String correctAnswer;
    private DocumentEntity document;
    private QuestionFeedbackModeEntity feedbacks;
}