package io.github.harvies.charon.spring.boot.webflux;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CharonWebFluxAutoConfiguration {
    
    @Bean
    public CustomWebFilter customWebFilter() {
        return new CustomWebFilter();
    }
}
