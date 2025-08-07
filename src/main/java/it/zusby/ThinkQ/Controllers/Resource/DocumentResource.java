package it.zusby.ThinkQ.Controllers.Resource;

import it.zusby.ThinkQ.Controllers.Service.DocumentService;
import it.zusby.ThinkQ.Types.Dto.DocumentCreateDTO;
import it.zusby.ThinkQ.Types.Dto.DocumentDTO;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.ResourceClosedException;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/document")
@Slf4j
public class DocumentResource {
    private final  DocumentService ds;

    @Autowired
    public DocumentResource(DocumentService ds) {
        this.ds = ds;
    }

    @PutMapping("/new")
    public ResponseEntity<DocumentDTO> newDocument(@RequestBody DocumentCreateDTO doc) {
        log.info("New document {}, {}", doc.getTitle(), LocalDateTime.now());

        try{
            return ResponseEntity.ok().body(new DocumentDTO());

        }catch (ServiceException e){
            throw new ResourceClosedException(e.getMessage());
        }
    }
}
