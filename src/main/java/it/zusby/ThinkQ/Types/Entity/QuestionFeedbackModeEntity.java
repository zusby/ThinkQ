package it.zusby.ThinkQ.Types.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity @Table(name="questions_feedbacks")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class QuestionFeedbackModeEntity extends AbstractEntity {

    private String comment;

    private boolean isCorrect;

    private LocalDateTime submittedAt;

    @OneToOne(mappedBy = "feedbacks")
    private GeneratedQuestionEntity generatedQuestion;
}