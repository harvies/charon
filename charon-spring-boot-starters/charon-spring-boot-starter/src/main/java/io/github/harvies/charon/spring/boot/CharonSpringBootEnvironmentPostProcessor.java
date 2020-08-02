package io.github.harvies.charon.spring.boot;

import io.github.harvies.charon.util.FileUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

@Slf4j
public class CharonSpringBootEnvironmentPostProcessor implements EnvironmentPostProcessor, Ordered {

    private ConfigurableEnvironment environment;
    /**
     * 配置文件位置，可扫描多个jar
     */
    public static final String CHARON_PROPERTIES_RESOURCE_LOCATION = "charon/application.properties";


    @SneakyThrows
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        this.environment = environment;
        log.info("set charon-spring-boot properties begin");
        loadProperties();
        log.info("set charon-spring-boot properties end");
    }

    @Override
    public int getOrder() {
        //要比springboot读取properties文件优先级低
        return ConfigFileApplicationListener.DEFAULT_ORDER + 1;
    }

    /**
     * 加载配置文件，参考 org.springframework.core.io.support.SpringFactoriesLoader#loadSpringFactories(java.lang.ClassLoader)
     */
    private void loadProperties() {
        ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
        try {
            Enumeration<URL> urls = (classLoader != null ?
                    classLoader.getResources(CHARON_PROPERTIES_RESOURCE_LOCATION) :
                    ClassLoader.getSystemResources(CHARON_PROPERTIES_RESOURCE_LOCATION));
            Properties properties = new Properties();
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                UrlResource resource = new UrlResource(url);
                properties.putAll(PropertiesLoaderUtils.loadProperties(resource));
            }
            //加载~/charon.properties
            try {
                FileUrlResource fileUrlResource = new FileUrlResource(FileUtils.getCurrentUserHomePath() + "/.charon.properties");
                Properties charonProperties = PropertiesLoaderUtils.loadProperties(fileUrlResource);
                properties.putAll(charonProperties);
            } catch (Exception e) {
                log.info("未配置~/charon.properties");
            }
            PropertiesPropertySource propertySource = new PropertiesPropertySource("charon-spring-boot", properties);
            //放入list尾部(取值时从头到尾查找，若application.properties没配置，则从该propertySource取值)
            environment.getPropertySources().addLast(propertySource);
        } catch (IOException ex) {
            throw new IllegalArgumentException("Unable to load properties from location [" +
                    CHARON_PROPERTIES_RESOURCE_LOCATION + "]", ex);
        }
    }
}
