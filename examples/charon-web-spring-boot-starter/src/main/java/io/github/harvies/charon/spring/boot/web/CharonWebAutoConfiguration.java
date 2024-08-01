package io.github.harvies.charon.spring.boot.web;

import io.github.harvies.charon.spring.boot.web.aop.LogAspect;
import io.github.harvies.charon.spring.boot.web.config.CharonWebMvcConfig;
import io.github.harvies.charon.spring.boot.web.config.ControllerConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@AutoConfiguration
@Import(value = {CharonWebMvcConfig.class, ControllerConfiguration.class})
public class CharonWebAutoConfiguration {

    @Bean
    public CharonWebApplicationListener charonWebApplicationListener() {
        return new CharonWebApplicationListener();
    }

    @Bean
    public LogAspect logAspect() {
        return new LogAspect();
    }
}
