package io.github.harvies.charon.spring.boot.web;

import io.github.harvies.charon.spring.boot.web.controller.system.HealthController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CharonWebAutoConfiguration {

    @Bean
    public CharonWebMvcConfig charonWebMvcConfig() {
        return new CharonWebMvcConfig();
    }

    @Bean
    public CharonWebApplicationListener charonWebApplicationListener() {
        return new CharonWebApplicationListener();
    }

    @Bean
    public HealthController healthController() {
        return new HealthController();
    }
}
