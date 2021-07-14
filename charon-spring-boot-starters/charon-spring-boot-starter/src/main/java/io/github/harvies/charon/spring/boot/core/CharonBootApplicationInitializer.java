package io.github.harvies.charon.spring.boot.core;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigRegistry;
import org.springframework.stereotype.Component;

@Component
public class CharonBootApplicationInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        if (configurableApplicationContext instanceof AnnotationConfigRegistry) {
            //扫描指定包
            ((AnnotationConfigRegistry) configurableApplicationContext).scan("io.github.harvies.charon.spring.boot.core");
        }
    }
}
