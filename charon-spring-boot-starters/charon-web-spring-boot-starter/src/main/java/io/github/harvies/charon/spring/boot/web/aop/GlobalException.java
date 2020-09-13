package io.github.harvies.charon.spring.boot.web.aop;

import lombok.Data;

/**
 * 全局异常类
 */
@Data
public class GlobalException extends RuntimeException {
    private String result;

    public GlobalException(String result) {
        super(result);
        this.result = result;

    }
}