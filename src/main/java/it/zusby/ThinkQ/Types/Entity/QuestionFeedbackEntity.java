package it.zusby.ThinkQ.Types.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity @Table(name="questions_feedbacks")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class QuestionFeedbackEntity extends AbstractEntity {

    private String comment;

    private String correctAnswer;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_generated_question", nullable = false)
    private GeneratedQuestionEntity generatedQuestion;
}