package io.github.harvies.charon.config;

import io.github.harvies.charon.config.event.ConfigChangeEvent;
import io.github.harvies.charon.config.event.ConfigProcessSuccessEvent;
import io.github.harvies.charon.spring.boot.core.SpringContextHolder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;

import java.util.Properties;
import java.util.concurrent.locks.LockSupport;

@Slf4j
public class CharonConfigEnvironmentPostProcessor implements EnvironmentPostProcessor, Ordered, ApplicationEventPublisherAware {

    public static final String COMMON = "common";
    private ConfigurableEnvironment environment;
    private ApplicationEventPublisher applicationEventPublisher;

    @SneakyThrows
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        this.environment = environment;
        log.info("set charon-config properties begin");
        //从配置中心加载配置
        loadPropertiesFromConfigCenter();
        log.info("set charon-config properties end");
    }

    @Override
    public int getOrder() {
        //要比springboot读取properties文件优先级低(先获取applicationName)
        return ConfigFileApplicationListener.DEFAULT_ORDER + 1;
    }


    private void loadPropertiesFromConfigCenter() {
        String appName = environment.getProperty("charon.application.name");
        String env = environment.getProperty("charon.env");
        if (StringUtils.isAnyBlank(appName, env)) {
            log.warn("appName or env is not config");
            return;
        }
        //common config
        loadPropertiesFromConfigCenter(COMMON, env);
        // app config
        loadPropertiesFromConfigCenter(appName, env);
    }

    private void loadPropertiesFromConfigCenter(String appName, String env) {
        App app = new App().setAppName(appName)
                .setEnv(EnvEnum.of(env));
        DefaultConfigClient configClient = new DefaultConfigClient.Builder()
                .setApp(app)
                .setCuratorFramework(ZkUtils.getClient()).build();
        loadPropertiesFromConfigCenter(configClient);
    }

    private void loadPropertiesFromConfigCenter(ConfigClient configClient) {
        App app = configClient.getApp();
        Properties properties = configClient.get();
        log.info("app:[{}]从配置中心加载到配置:[{}]", configClient.getApp(), properties);
        String key = "charon-spring-boot-config-center-" + app.getAppName();
        PropertiesPropertySource propertySource = new PropertiesPropertySource(key, properties);
        //配置放到最前
        environment.getPropertySources().addFirst(propertySource);
        log.info("load [{}] item config from config center success!,env:[{}] appName:[{}],data:[{}]", properties.size(), app.getEnv(), app.getAppName(), properties);
        Thread thread = new Thread(() -> {
            configClient.watch(event -> {
                if (event instanceof ConfigChangeEvent) {
                    this.environment.getPropertySources().addFirst(new PropertiesPropertySource(key, configClient.get()));
                    //清空BeanRefreshScope中所有bean的缓存,下次再获取bean的时候重新实例化(重写解析@Value)
                    BeanRefreshScope.CACHE_LOCK.writeLock().lock();
                    try {
                        BeanRefreshScope.clean();
                    } finally {
                        BeanRefreshScope.CACHE_LOCK.writeLock().unlock();
                    }
                    //更新@Value注解修饰字段值
                    valueAnnotationProcessor();
                    //发送配置处理成功事件
                    ConfigProcessSuccessEvent configProcessSuccessEvent = new ConfigProcessSuccessEvent(event);
                    applicationEventPublisher.publishEvent(configProcessSuccessEvent);
                }
            });
            LockSupport.park();
        });
        thread.setName("config-center-listen-thread-" + app.getEnv().getCode() + "-" + app.getAppName());
        thread.setDaemon(true);
        thread.start();
    }

    private void valueAnnotationProcessor() {
        SpringContextHolder.getBean(ValueAnnotationProcessor.class).processor();
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
