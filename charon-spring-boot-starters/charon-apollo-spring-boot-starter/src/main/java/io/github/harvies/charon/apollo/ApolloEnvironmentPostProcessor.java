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

        //set app.id
        String applicationName = environment.getProperty("charon.application.name");
        if (StringUtils.isBlank(applicationName)) {
            throw new RuntimeException("charon.application.name is not null");
        }
        System.setProperty("app.id", applicationName);
        System.setProperty("spring.application.name", applicationName);

        //set apollo.meta
        String env = environment.getProperty("charon.env");
        if (StringUtils.isBlank(env)) {
            throw new RuntimeException("properties env is not null");
        }

        System.setProperty("spring.profiles.active", env);

        Properties properties = new Properties();
        @Cleanup InputStream inputStream = new ClassPathResource("charon-apollo.properties").getInputStream();
        properties.load(inputStream);
        //load other properties
        System.setProperty("env", env);
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            System.setProperty(entry.getKey().toString(), entry.getValue().toString());
        }
        log.info("set apollo properties end");
    }

    @Override
    public int getOrder() {
        //要比springboot读取properties文件优先级低
        return ConfigFileApplicationListener.DEFAULT_ORDER + 1;
    }
}
