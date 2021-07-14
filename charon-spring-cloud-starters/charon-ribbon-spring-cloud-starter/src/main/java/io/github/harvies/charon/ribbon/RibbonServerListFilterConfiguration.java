package io.github.harvies.charon.ribbon;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RibbonServerListFilterConfiguration {
    @Bean
    public CharonRule charonRule() {
        return new CharonRule();
    }
}
