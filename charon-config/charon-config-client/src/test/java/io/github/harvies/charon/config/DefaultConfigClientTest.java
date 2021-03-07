package io.github.harvies.charon.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Properties;
import java.util.concurrent.locks.LockSupport;

@Slf4j
class DefaultConfigClientTest {

    protected App app = new App().setAppName("charon").setEnv(EnvEnum.DEV);
    private DefaultConfigClient configClient = new DefaultConfigClient.Builder()
            .setApp(app).setCuratorFramework(ZkUtils.getClient())
            .build();

    @Test
    void get() {
        Properties properties = configClient.get();
        log.info("properties:[{}]", properties);
    }

    @Test
    void testGet() {
        String value = configClient.get("testKey");
        log.info("value:[{}]", value);
    }

    @Test
    void watch() {
        configClient.watch(configEvent -> log.info("configEvent:[{}]", configEvent));
        LockSupport.park();
    }
}
