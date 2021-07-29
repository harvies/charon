package io.github.harvies.charon.spring.boot.lifecycle.config;

import io.github.harvies.charon.spring.boot.lifecycle.BeanA;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public BeanA beanA() {
        return new BeanA();
    }

}
