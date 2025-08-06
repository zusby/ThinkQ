package it.zusby.ThinkQ.Resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestResource {

    public TestResource() {
    }

    @GetMapping("")
    public String test() {
        log.info("GET request from TEST at {}", LocalDateTime.now());
        return "test";
    }
}
