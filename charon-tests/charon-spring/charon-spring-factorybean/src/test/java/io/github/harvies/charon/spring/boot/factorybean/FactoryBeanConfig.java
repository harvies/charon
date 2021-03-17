package io.github.harvies.charon.spring.boot.factorybean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class FactoryBeanConfig {

    @Bean
    //控制TestFactoryBean是单例还是原型
    @Scope(scopeName = "prototype")
    public TestFactoryBean testFactoryBean() {
        return new TestFactoryBean();
    }
}
