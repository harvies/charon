package io.github.harvies.test.spring.boot.lifecycle.config;

import io.github.harvies.test.spring.boot.lifecycle.BeanA;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public BeanA beanA() {
        return new BeanA();
    }

}
