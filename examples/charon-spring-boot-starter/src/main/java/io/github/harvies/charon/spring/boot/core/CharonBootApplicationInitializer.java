package io.github.harvies.charon.spring.boot.core;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigRegistry;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CharonBootApplicationInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @PostConstruct
    public void init() {
        log.warn("CharonBootApplicationInitializer inited");
    }

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {

        log.warn("CharonBootApplicationInitializer initialize");

        if (configurableApplicationContext instanceof AnnotationConfigRegistry) {
            log.warn("CharonBootApplicationInitializer 可手动扫描指定包");
            //扫描指定包
            ((AnnotationConfigRegistry) configurableApplicationContext).scan("io.github.harvies.charon.spring.boot.core");
        }
    }
}
