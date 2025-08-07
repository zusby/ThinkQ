package it.zusby.ThinkQ.Types.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class DocumentModel extends AbstractModel{

    private String Title;
    private String content;
    private List<GeneratedQuestionModel> questions;
}
