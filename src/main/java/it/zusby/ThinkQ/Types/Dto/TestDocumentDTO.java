package it.zusby.ThinkQ.Types.Dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
@Data
public class TestDocumentDTO implements Serializable {
    private String textContent;

    private String question;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;

    private String correctAnswer;
}
