package io.github.harvies.charon.spring.boot.web.config;

import io.github.harvies.charon.spring.boot.web.controller.system.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerConfiguration {

    @Bean
    public HealthController healthController() {
        return new HealthController();
    }

    @Bean
    public LogController logController() {
        return new LogController();
    }

    @Bean
    public MemoryController memoryController() {
        return new MemoryController();
    }

    @Bean
    public RuntimeController runtimeController() {
        return new RuntimeController();
    }

    @Bean
    public ThreadController threadController() {
        return new ThreadController();
    }
}