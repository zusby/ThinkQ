package it.zusby.ThinkQ.Types.Entity;

import jakarta.persistence.Entity;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class DocumentEntity  extends AbstractEntity {


    private String Title;

    private String content;

    private List<GeneratedQuestionEntity> questions;
}
