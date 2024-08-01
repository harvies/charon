package io.github.harvies.charon.spring.boot.core;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@Slf4j
@AutoConfiguration
@EnableConfigurationProperties({CharonBootProperties.class, CharonBootApplicationProperties.class})
public class CharonBootAutoConfiguration {

    @PostConstruct
    public void init() {
        log.warn("CharonBootAutoConfiguration inited");
    }


    @Bean
    public CharonShutdownHook charonShutdownHook() {
        return new CharonShutdownHook();
    }

}
