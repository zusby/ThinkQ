package it.zusby.ThinkQ.Types.Dto;

import it.zusby.ThinkQ.Types.Model.AbstractModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class DocumentDTO extends AbstractModel {
    private String Title;
    private String content;
    private List<GeneratedQuestionDTO> questions;
}
