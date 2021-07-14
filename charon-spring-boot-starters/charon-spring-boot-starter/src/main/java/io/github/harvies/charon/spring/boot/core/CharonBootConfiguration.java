package io.github.harvies.charon.spring.boot.core;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({CharonBootProperties.class, CharonBootApplicationProperties.class})
public class CharonBootConfiguration {

    @Bean
    public CharonShutdownHook charonShutdownHook() {
        return new CharonShutdownHook();
    }

}
