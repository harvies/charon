package io.github.harvies.charon.dubbo;

import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.spring.boot.env.DubboDefaultPropertiesEnvironmentPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class DubboExtraPropertiesEnvironmentPostProcessor extends DubboDefaultPropertiesEnvironmentPostProcessor {

    @SneakyThrows
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        log.info("set dubbo properties begin");
        Properties properties = new Properties();
        @Cleanup InputStream inputStream = new ClassPathResource("charon-dubbo/application.properties").getInputStream();
        properties.load(inputStream);
        PropertiesPropertySource propertiesPropertySource = new PropertiesPropertySource(DubboDefaultPropertiesEnvironmentPostProcessor.PROPERTY_SOURCE_NAME, properties);
        environment.getPropertySources().addLast(propertiesPropertySource);
        super.postProcessEnvironment(environment, application);
        log.info("set dubbo properties end");
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}