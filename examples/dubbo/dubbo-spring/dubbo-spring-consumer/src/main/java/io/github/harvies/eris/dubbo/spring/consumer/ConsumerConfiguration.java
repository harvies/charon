package io.github.harvies.eris.dubbo.spring.consumer;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableDubbo(scanBasePackages = "io.github.harvies.eris.dubbo.spring.consumer")
@PropertySource("classpath:/spring/dubbo-consumer.properties")
@ComponentScan(value = {"io.github.harvies.eris.dubbo.spring.consumer"})
 public class ConsumerConfiguration {

}