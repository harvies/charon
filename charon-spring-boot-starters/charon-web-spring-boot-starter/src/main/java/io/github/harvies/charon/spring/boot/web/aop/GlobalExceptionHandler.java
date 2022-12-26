package io.github.harvies.charon.spring.boot.web.aop;

import cn.hutool.core.exceptions.ExceptionUtil;
import io.github.harvies.charon.model.ApiResult;
import io.github.harvies.charon.model.constant.CharonErrorCode;
import io.github.harvies.charon.model.exception.ApiException;
import io.github.harvies.charon.model.exception.SystemException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.annotation.PostConstruct;
@RestControllerAdvice
@Order(-1000000)
@Slf4j
public class GlobalExceptionHandler {

    @PostConstruct
    public void init() {
        log.info("GlobalExceptionHandler init");
    }

    public GlobalExceptionHandler() {
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({ApiException.class})
    public ApiResult apiExceptionHandler(HttpServletRequest req, ApiException e) {
        ApiResult apiResult = ApiResult.error(e.getCode(), e.getMessage(), e.getParams());
        log.warn("---apiExceptionHandler--- Host:{},invokes url:{},[errorCode:{},msg:{}]", req.getRemoteHost(), req.getRequestURL(), apiResult.getCode(), apiResult.getMsg());
        return apiResult;
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({SystemException.class})
    public ApiResult systemExceptionHandler(HttpServletRequest req, SystemException e) {
        ApiResult apiResult = ApiResult.error(e.getCode(), e.getMessage());
        log.warn("---systemExceptionHandler--- Host:{},invokes url:{},[errorCode:{},msg:{}]", req.getRemoteHost(), req.getRequestURL(), apiResult.getCode(), apiResult.getMsg());
        return apiResult;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NoHandlerFoundException.class})
    public ApiResult noHandlerFoundExceptionHandler(HttpServletRequest req, NoHandlerFoundException e) {
        log.warn("---NoHandlerFoundExceptionHandler--- Host: {} invokes url: {}, message: {}", req.getRemoteHost(), req.getRequestURL(), e.getMessage());
        return ApiResult.error(CharonErrorCode.PATH_ERROR);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({Throwable.class})
    public ApiResult defaultExceptionHandler(HttpServletRequest req, Throwable e) {
        Throwable t = ExceptionUtil.getRootCause(e);
        log.error("---DefaultExceptionHandler--- Host: {} invokes url: {}, message:{}, error: {}", req.getRemoteHost(), req.getRequestURL(), t.getMessage(), e);
        return ApiResult.error(CharonErrorCode.SYSTEM_ERROR);
    }
}
