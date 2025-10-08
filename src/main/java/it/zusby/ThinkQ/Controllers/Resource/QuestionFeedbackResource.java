package it.zusby.ThinkQ.Controllers.Resource;

import it.zusby.ThinkQ.Controllers.Service.DocumentService;
import it.zusby.ThinkQ.Controllers.Service.QuestionFeedbackService;
import it.zusby.ThinkQ.Types.Dto.QuestionFeedbackDTO;
import it.zusby.ThinkQ.Types.Model.QuestionFeedbackModel;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Response;
import org.hibernate.ResourceClosedException;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/feedback")
@Slf4j
public class QuestionFeedbackResource {
    private final QuestionFeedbackService qs;

    @Autowired
    public QuestionFeedbackResource(QuestionFeedbackService service) {
        this.qs = service;
    }


    @GetMapping("/{id}")
    public ResponseEntity<QuestionFeedbackDTO> getFeedbackById(@PathVariable UUID id) {
        log.info("get feedback {}, {}", id, LocalDateTime.now());

        try {
            return ResponseEntity.ok(this.qs.getFeedbackById(id));
        } catch (ServiceException e) {
            throw new ResourceClosedException(e.getMessage());
        }
    }


    @PostMapping("/new")
    public ResponseEntity<QuestionFeedbackDTO> createFeedback(@RequestBody QuestionFeedbackDTO dto) {
        log.info("Create feedback for question  {}, {}", dto.getQuestionId(), LocalDateTime.now());

        try {
            return ResponseEntity.ok(this.qs.createFeedback(dto));
        } catch (ServiceException e) {
            throw new ResourceClosedException(e.getMessage());
        }
    }
}


