package io.github.harvies.eris.dubbo.spring.provider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableDubbo(scanBasePackages = "io.github.harvies.eris.dubbo.spring.provider.service.impl")
@PropertySource("classpath:/spring/dubbo-provider.properties")
public class ProviderConfiguration {

}