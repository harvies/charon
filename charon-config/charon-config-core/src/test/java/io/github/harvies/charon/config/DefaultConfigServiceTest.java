package io.github.harvies.charon.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Properties;
import java.util.concurrent.locks.LockSupport;

@Slf4j
class DefaultConfigServiceTest {

    App app = new App().setAppName("charon").setEnv(EnvEnum.DEV);
    DefaultConfigService defaultConfigService = new DefaultConfigService.Builder()
            .setCuratorFramework(ZkUtils.getClient())
            .build();

    @Test
    public void get() {
        Properties properties = defaultConfigService.get(app);
        log.info("properties:[{}]", properties);
    }

    @Test
    void watch() {
        defaultConfigService.watch(app, event -> {
            log.warn("event:[]", event);
        });
        LockSupport.park();
    }
}
