package io.github.harvies.charon.spring.boot.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
public class CharonWebApplicationInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
//        if (configurableApplicationContext instanceof AnnotationConfigRegistry) {
//            ((AnnotationConfigRegistry) configurableApplicationContext).scan("io.github.harvies.charon.spring.boot.web");
//        }
    }
}
