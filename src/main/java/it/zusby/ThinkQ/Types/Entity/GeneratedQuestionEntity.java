package it.zusby.ThinkQ.Types.Entity;

import jakarta.persistence.*;
import lombok.*;


@Entity @Table(name="generated_questions")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class GeneratedQuestionEntity extends AbstractEntity {

    private String questionText;
    private String sourceText;

    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String correctAnswer;

    @ManyToOne
    @JoinColumn(name = "document_id")
    private DocumentEntity document;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_question_feedback")
    private QuestionFeedbackEntity feedbacks;
}