package it.zusby.ThinkQ.Types.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;


@Entity @Table(name="questions_feedbacks")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class QuestionFeedbackModeEntity extends AbstractEntity {
    String comment;
    boolean isCorrect;
    LocalDateTime submittedAt;
}