package it.zusby.ThinkQ.Types.Dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class DocumentCreateDTO extends AbstractCreateDTO {
    private String Title;
    private String content;
    private List<GeneratedQuestionDTO> questions;
}
