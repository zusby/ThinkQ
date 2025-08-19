package it.zusby.ThinkQ.Controllers.Resource;

import it.zusby.ThinkQ.Controllers.Service.DocumentService;
import it.zusby.ThinkQ.Mappers.DocumentModelMapper;
import it.zusby.ThinkQ.Types.Dto.DocumentCreateDTO;
import it.zusby.ThinkQ.Types.Dto.DocumentDTO;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.ResourceClosedException;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/document")
@Slf4j
public class DocumentResource {
    private final  DocumentService ds;

    @Autowired
    public DocumentResource(DocumentService ds, @Qualifier("documentModelMapperImpl") DocumentModelMapper mapper) {
        this.ds = ds;
    }

    @PutMapping("/new")
    public ResponseEntity<DocumentDTO> newDocument(@RequestBody DocumentCreateDTO doc) {
        log.info("New document {}, {}", doc.getTitle(), LocalDateTime.now());

        try{
            ds.generateQuestions(doc);
            return ResponseEntity.ok().body(new DocumentDTO());

        }catch (ServiceException e){
            throw new ResourceClosedException(e.getMessage());
        }
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<DocumentDTO> newDocument(@@PathVariable String id) {
        log.info("get document {}, {}", id, LocalDateTime.now());

        try{
            var doc = ds.getDocument(id);
            return ResponseEntity.ok().body(new DocumentDTO());
        }
        catch (ServiceException e){
            throw new ResourceClosedException(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DocumentDTO> newDocument(@@PathVariable String id) {
        log.info("get document {}, {}", id, LocalDateTime.now());

        try{
            var doc = ds.delete(id);
            return ResponseEntity.ok();
        }
        catch (ServiceException e){
            throw new ResourceClosedException(e.getMessage());
        }
    }
}
