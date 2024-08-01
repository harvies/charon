package io.github.harvies.test.spring.boot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FactoryBeanConfig {

    @Bean(name = "beanAFactoryBean")
    //控制TestFactoryBean是单例还是原型
//    @Scope(scopeName = "prototype")
    public BeanAFactoryBean testFactoryBean() {
        return new BeanAFactoryBean();
    }
}
