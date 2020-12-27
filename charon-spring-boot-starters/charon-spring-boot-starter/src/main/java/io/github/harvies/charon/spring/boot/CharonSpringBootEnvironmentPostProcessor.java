package io.github.harvies.charon.spring.boot;

import io.github.harvies.charon.config.*;
import io.github.harvies.charon.config.event.ConfigChangeEvent;
import io.github.harvies.charon.util.PropertiesUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
import java.util.concurrent.locks.LockSupport;

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
        //从配置中心加载配置
        loadPropertiesFromConfigCenter();
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
            //加载charon.properties
            try {
                FileUrlResource fileUrlResource = new FileUrlResource(PropertiesUtils.getDefaultPath());
                Properties charonProperties = PropertiesLoaderUtils.loadProperties(fileUrlResource);
                properties.putAll(charonProperties);
            } catch (Exception e) {
                log.info("未配置" + PropertiesUtils.getDefaultPath());
            }
            PropertiesPropertySource propertySource = new PropertiesPropertySource("charon-spring-boot", properties);
            //放入list尾部(取值时从头到尾查找，若application.properties没配置，则从该propertySource取值)
            environment.getPropertySources().addLast(propertySource);
        } catch (IOException ex) {
            throw new IllegalArgumentException("Unable to load properties from location [" +
                    CHARON_PROPERTIES_RESOURCE_LOCATION + "]", ex);
        }
    }

    private void loadPropertiesFromConfigCenter() {
        String appName = environment.getProperty("spring.application.name");
        String env = environment.getProperty("charon.env");
        if (StringUtils.isAnyBlank(appName, env)) {
            log.warn("appName or env is not config");
            return;
        }
        DefaultConfigService defaultConfigService = new DefaultConfigService.Builder()
                .setCuratorFramework(ZkUtils.getClient())
                .build();
        //common config
        loadPropertiesFromConfigCenter("common", env, defaultConfigService);
        // app config
        loadPropertiesFromConfigCenter(appName, env, defaultConfigService);
    }

    private void loadPropertiesFromConfigCenter(String appName, String env, DefaultConfigService defaultConfigService) {
        Properties properties = defaultConfigService.get(new App().setAppName(appName).setEnv(EnvEnum.of(env)));
        String key = "charon-spring-boot-config-center-" + appName;
        PropertiesPropertySource propertySource = new PropertiesPropertySource(key, properties);
        //配置放到最前
        environment.getPropertySources().addFirst(propertySource);
        log.info("load [{}] item config from config center success!,env:[{}] appName:[{}],data:[{}]", properties.size(), env, appName, properties);
        App app = new App().setAppName(appName).setEnv(EnvEnum.of(env));
        Thread thread = new Thread(() -> {
            defaultConfigService.watch(app, event -> {
                if (event instanceof ConfigChangeEvent) {
                    this.environment.getPropertySources().addFirst(new PropertiesPropertySource(key, defaultConfigService.get(app)));
                }
            });
            LockSupport.park();
        });
        thread.setName("config-center-listen-thread");
        thread.setDaemon(true);
        thread.start();
    }
}
