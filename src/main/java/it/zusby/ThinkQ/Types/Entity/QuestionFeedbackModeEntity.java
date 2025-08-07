package it.zusby.ThinkQ.Types.Entity;

import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class QuestionFeedbackModeEntity extends AbstractEntity {
    String comment;
    boolean isCorrect;
    LocalDateTime submittedAt;
}