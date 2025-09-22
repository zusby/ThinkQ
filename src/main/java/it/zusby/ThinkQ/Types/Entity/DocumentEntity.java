package it.zusby.ThinkQ.Types.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@Table(name="documents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocumentEntity  extends AbstractEntity {


    private String title;

    private String content;


    @OneToMany(mappedBy = "id", cascade = ALL, orphanRemoval = true)
    @Column(name = "questions_id")
    private List<GeneratedQuestionEntity> questions;
}
