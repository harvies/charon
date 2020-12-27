package io.github.harvies.charon.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Properties;

@Slf4j
class DefaultConfigServiceTest {

    @Test
    public void get() {
        DefaultConfigService defaultConfigService = new DefaultConfigService.Builder()
                .setCuratorFramework(ZkUtils.getClient())
                .build();
        Properties properties = defaultConfigService.get(new App().setAppName("charon").setEnv(EnvEnum.DEV));
        log.info("properties:[{}]", properties);
    }
}
