package io.github.harvies.charon.spring.boot.web;

import io.github.harvies.charon.spring.boot.web.config.serialize.FastJsonAutoConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ImportAutoConfiguration(value = {ControllerConfiguration.class, FastJsonAutoConfiguration.class})
public class CharonWebAutoConfiguration {

    @Bean
    public CharonWebMvcConfig charonWebMvcConfig() {
        return new CharonWebMvcConfig();
    }

    @Bean
    public CharonWebApplicationListener charonWebApplicationListener() {
        return new CharonWebApplicationListener();
    }
}
