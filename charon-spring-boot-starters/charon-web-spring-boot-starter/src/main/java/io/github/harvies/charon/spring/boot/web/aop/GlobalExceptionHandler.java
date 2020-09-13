package io.github.harvies.charon.spring.boot.web.aop;

import io.github.harvies.charon.spring.boot.web.result.ResultDTO;
import io.github.harvies.charon.spring.boot.web.result.Results;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultDTO<String> exceptionHandler(Exception e) {
        if (e instanceof GlobalException) {
            return Results.failed(e.getMessage());
        }
        log.warn("exceptionHandler", e);
        return Results.failed(null);
    }

}