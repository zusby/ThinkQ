package it.zusby.ThinkQ.Types.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
public class TestDocumentModel implements Serializable {

    private String textContent;

    private String question1;
    private String question2;
    private String question3;
    private String question4;

    private String correctAnswer;
}
