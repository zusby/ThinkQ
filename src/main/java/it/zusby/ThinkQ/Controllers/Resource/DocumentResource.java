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

    @PostMapping("/new")
    public ResponseEntity<DocumentDTO> newDocument(@RequestBody DocumentCreateDTO doc) {
        log.info("New document {}, {}", doc.getTitle(), LocalDateTime.now());

        try{
            return ResponseEntity.ok().body(ds.generateQuestions(doc));
        }catch (ServiceException e){
            throw new ResourceClosedException(e.getMessage());
        }
    }
    @GetMapping("/get/{id}")
    public DocumentDTO getDocument(@PathVariable String id) {
        log.info("get document {}, {}", id, LocalDateTime.now());

        try{
            return ds.getDocument(id);
        }
        catch (ServiceException e){
            throw new ResourceClosedException(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteDocument(@PathVariable String id) {
        log.info("delete document {}, {}", id, LocalDateTime.now());

        try{
            ds.delete(id);
            return ResponseEntity.ok().body(Boolean.TRUE);
        }
        catch (ServiceException e){
            throw new ResourceClosedException(e.getMessage());
        }
    }
}
