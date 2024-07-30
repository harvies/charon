package io.github.harvies.charon.config;

import io.github.harvies.charon.config.annotation.RefreshScope;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(RefreshScope.class)
public class RefreshAutoConfiguration {
    @Resource
    private ConfigurableListableBeanFactory beanFactory;

    @PostConstruct
    public void init() {
        beanFactory.registerScope(BeanRefreshScope.SCOPE_REFRESH, BeanRefreshScope.getInstance());
    }

    @Bean
    public ValueAnnotationProcessor valueAnnotationProcessor() {
        return new ValueAnnotationProcessor();
    }
}
