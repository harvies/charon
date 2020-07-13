package io.github.harvies.charon.spring.boot;

import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class CharonSpringBootEnvironmentPostProcessor implements EnvironmentPostProcessor, Ordered {

    @SneakyThrows
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {

        log.info("set charon-spring-boot properties begin");
        Properties properties = new Properties();
        @Cleanup InputStream inputStream = new ClassPathResource("charon/application.properties").getInputStream();
        properties.load(inputStream);
        PropertiesPropertySource propertySource = new PropertiesPropertySource("charon-spring-boot", properties);
        //放入list尾部(取值时从头到尾查找，若application.properties没配置，则从该propertySource取值)
        environment.getPropertySources().addLast(propertySource);
        log.info("set apollo properties end");
    }

    @Override
    public int getOrder() {
        //要比springboot读取properties文件优先级低
        return ConfigFileApplicationListener.DEFAULT_ORDER + 1;
    }
}
