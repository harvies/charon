package io.github.harvies.charon.spring.boot.fastjson;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigRegistry;

@Slf4j
public class FastJsonApplicationInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        if (configurableApplicationContext instanceof AnnotationConfigRegistry) {
            ((AnnotationConfigRegistry) configurableApplicationContext).scan("io.github.harvies.charon.spring.boot.fastjson");
        }
    }
}
