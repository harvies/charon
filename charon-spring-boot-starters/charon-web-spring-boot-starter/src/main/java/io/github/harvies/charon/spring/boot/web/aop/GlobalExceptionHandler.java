package io.github.harvies.charon.spring.boot.web.aop;

import io.github.harvies.charon.spring.boot.web.result.ResultDTO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

// TODO: 2020/9/13
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultDTO<String> errorHandler(Exception e) {
        return new ResultDTO<>("系统异常!");        //将异常信息响应给浏览器
    }
}