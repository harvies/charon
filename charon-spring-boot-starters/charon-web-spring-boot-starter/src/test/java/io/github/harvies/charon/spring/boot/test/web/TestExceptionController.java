package io.github.harvies.charon.spring.boot.test.web;

import io.github.harvies.charon.spring.boot.web.aop.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/exception")
public class TestExceptionController {
    
    public static final String RESULT = "aaa";

    @RequestMapping(value = "/global", produces = MediaType.TEXT_PLAIN_VALUE)
    public String status() {
        throw new GlobalException(RESULT);
    }
}
