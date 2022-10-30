package io.github.harvies.charon.spring.boot.web;

import io.github.harvies.charon.spring.boot.web.aop.LogAspect;
import io.github.harvies.charon.spring.boot.web.config.CharonWebMvcConfig;
import io.github.harvies.charon.spring.boot.web.config.serialize.FastJsonAutoConfiguration;
import io.github.harvies.charon.spring.boot.web.config.serialize.WebMvcConfig;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ImportAutoConfiguration(value = {FastJsonAutoConfiguration.class, CharonWebMvcConfig.class, WebMvcConfig.class})
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
