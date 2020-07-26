package io.github.harvies.charon.spring.boot.web;

import io.github.harvies.charon.spring.boot.web.interceptor.CharonWebHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CharonWebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CharonWebHandlerInterceptor());
    }
}
