package io.github.harvies.charon.spring.boot.webflux.aop;

import io.github.harvies.charon.model.ApiResult;
import io.github.harvies.charon.model.constant.CharonErrorCode;
import io.github.harvies.charon.model.exception.ApiException;
import io.github.harvies.charon.model.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(ApiException.class)
    @ResponseBody
    public ApiResult<String> apiExceptionHandler(Exception e) {
        log.warn("apiExceptionHandler", e);
        return ApiResult.error(CharonErrorCode.SYSTEM_ERROR);
    }
    @ExceptionHandler(SystemException.class)
    @ResponseBody
    public ApiResult<String> systemExceptionHandler(Exception e) {
        log.warn("systemExceptionHandler", e);
        return ApiResult.error(CharonErrorCode.SYSTEM_ERROR);
    }

}
