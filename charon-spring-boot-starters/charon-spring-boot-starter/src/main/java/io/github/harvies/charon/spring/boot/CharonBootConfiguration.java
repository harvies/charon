package io.github.harvies.charon.spring.boot;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({CharonBootProperties.class, CharonBootApplicationProperties.class})
public class CharonBootConfiguration {


}
