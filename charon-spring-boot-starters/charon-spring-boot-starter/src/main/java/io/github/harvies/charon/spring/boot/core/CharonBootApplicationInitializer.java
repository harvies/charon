package io.github.harvies.charon.spring.boot.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigRegistry;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
@Slf4j
@Component
public class CharonBootApplicationInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
   @PostConstruct
   public void init(){
       log.info("CharonBootApplicationInitializer init");
   }
    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        if (configurableApplicationContext instanceof AnnotationConfigRegistry) {
            //扫描指定包
            ((AnnotationConfigRegistry) configurableApplicationContext).scan("io.github.harvies.charon.spring.boot.core");
        }
    }
}
