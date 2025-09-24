package it.zusby.ThinkQ.Types.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity @Table(name="questions_feedbacks")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class QuestionFeedbackModeEntity extends AbstractEntity {

    private String comment;

    private boolean isCorrect;

    private LocalDateTime submittedAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id")
    private GeneratedQuestionEntity generatedQuestion;
}