package io.github.harvies.charon.apollo;

import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

@Slf4j
public class ApolloEnvironmentPostProcessor implements EnvironmentPostProcessor, Ordered {

    @SneakyThrows
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        log.info("set apollo properties begin");
        //set app.id move to charon/application.properties
        String applicationName = environment.getProperty("charon.application.name");
        if (StringUtils.isBlank(applicationName)) {
            throw new RuntimeException("properties charon.application.name is not null");
        }
        System.setProperty("app.id", applicationName);
        //set apollo.meta move to charon/application.properties
        String env = environment.getProperty("charon.env");
        if (StringUtils.isBlank(env)) {
            throw new RuntimeException("properties charon.env is not null");
        }
        System.setProperty("env", env);
        log.info("set apollo properties end");
    }

    @Override
    public int getOrder() {
        //要比springboot读取properties文件优先级低
        return ConfigFileApplicationListener.DEFAULT_ORDER + 1;
    }
}
