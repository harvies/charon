package io.github.harvies.test.spring.boot.webflux;

import io.github.harvies.charon.model.exception.ApiException;
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
        throw new ApiException(RESULT);
    }
}
