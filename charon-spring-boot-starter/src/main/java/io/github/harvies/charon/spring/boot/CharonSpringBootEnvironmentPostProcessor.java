package io.github.harvies.charon.spring.boot;

import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.OriginTrackedMapPropertySource;
import org.springframework.boot.env.RandomValuePropertySource;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

@Slf4j
public class CharonSpringBootEnvironmentPostProcessor implements EnvironmentPostProcessor, Ordered {

    @SneakyThrows
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        log.info("set charon-spring-boot properties begin");

        Properties properties = new Properties();
        @Cleanup InputStream inputStream = new ClassPathResource("charon-spring-boot.properties").getInputStream();
        properties.load(inputStream);
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            System.setProperty(entry.getKey().toString(), entry.getValue().toString());
        }
        OriginTrackedMapPropertySource originTrackedMapPropertySource = new OriginTrackedMapPropertySource("charon-spring-boot", properties);
        environment.getPropertySources().addLast(originTrackedMapPropertySource);
        log.info("set apollo properties end");
    }

    @Override
    public int getOrder() {
        //要比springboot读取properties文件优先级低
        return ConfigFileApplicationListener.DEFAULT_ORDER + 1;
    }
}
