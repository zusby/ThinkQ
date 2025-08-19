package it.zusby.ThinkQ.Types.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;


@Entity @Table(name="generated_questions")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class GeneratedQuestionEntity extends AbstractEntity {

    private String questionText;
    private String sourceText;

    private List<String> options;
    private String correctAnswer;

    @ManyToOne(cascade= CascadeType.ALL)
    private DocumentEntity document;

    @OneToOne(mappedBy="id")
    private QuestionFeedbackModeEntity feedbacks;
}