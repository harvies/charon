package io.github.harvies.charon.gray;

import io.github.harvies.charon.gray.feign.CharonRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CharonGrayAutoConfiguration {

    @Bean
    public CustomWebFilter customWebFilter() {
        return new CustomWebFilter();
    }

    @Bean
    public CharonRequestInterceptor charonRequestInterceptor() {
        return new CharonRequestInterceptor();
    }
}
