package io.github.harvies.charon.spring.boot.webflux.aop;

import lombok.Data;

/**
 * 全局异常类
 */
@Data
public class GlobalException extends RuntimeException {
    
    public GlobalException(String message) {
        super(message);
    }
}