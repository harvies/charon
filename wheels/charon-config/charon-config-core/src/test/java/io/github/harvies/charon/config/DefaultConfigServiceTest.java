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
    public void get2() {
        String value = defaultConfigService.get(app, "aaaaa");
        log.info("value:[{}]", value);
    }


    @Test
    void watch() {
        defaultConfigService.watch(app, event -> log.warn("event:[{}]", event));
        LockSupport.park();
    }

    @Test
    void update() {
        String key = "testKey";
        defaultConfigService.update(app, new ConfigData().setKey(key).setValue("testValue"));
    }

    @Test
    void delete() {
        defaultConfigService.delete(app, "testKey");
    }

    @Test
    void deleteAll() {
        defaultConfigService.deleteAll(app);
    }

}
