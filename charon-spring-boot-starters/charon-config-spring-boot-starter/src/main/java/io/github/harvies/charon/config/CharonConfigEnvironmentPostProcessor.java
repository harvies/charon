package io.github.harvies.charon.config;

import io.github.harvies.charon.config.event.ConfigChangeEvent;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;

import java.util.Properties;
import java.util.concurrent.locks.LockSupport;

@Slf4j
public class CharonConfigEnvironmentPostProcessor implements EnvironmentPostProcessor, Ordered {

    private ConfigurableEnvironment environment;

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
                    //清空BeanRefreshScope中所有bean的缓存,下次再获取bean的时候重新实例化(重写解析@Value)
                    BeanRefreshScope.clean();
                }
            });
            LockSupport.park();
        });
        thread.setName("config-center-listen-thread");
        thread.setDaemon(true);
        thread.start();
    }
}
