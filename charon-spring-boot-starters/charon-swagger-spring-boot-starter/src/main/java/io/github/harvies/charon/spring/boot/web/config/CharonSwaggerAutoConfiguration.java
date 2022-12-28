package io.github.harvies.charon.spring.boot.web.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({CharonSwaggerProperties.class})
@Import({
        Swagger2Config.class
})
public class CharonSwaggerAutoConfiguration {


}
