package it.zusby.ThinkQ.Controllers.Resource;

import it.zusby.ThinkQ.Controllers.Service.DocumentService;
import it.zusby.ThinkQ.Types.Dto.TestDocumentDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestResource {
    private final DocumentService service;
    @Autowired
    public TestResource(DocumentService service) {
        this.service = service;
    }

    @PutMapping("/generate")
    public TestDocumentDTO testDocument(@RequestBody TestDocumentDTO dto) {
        log.info("GET request from TEST at {}", LocalDateTime.now());
        return this.service.generateQuestions(dto);


    }
}
