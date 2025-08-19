package it.zusby.ThinkQ.Types.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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


    @OneToMany(cascade=ALL, mappedBy="id")
    private List<GeneratedQuestionEntity> questions;
}
