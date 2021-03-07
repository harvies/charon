package io.github.harvies.charon.config;

import com.google.common.base.Preconditions;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;

import java.util.Properties;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DefaultConfigClient implements ConfigClient {

    @Setter
    private App app;
    @Setter
    private ConfigService configService;

    @Override
    public App getApp() {
        return app;
    }

    @Override
    public Properties get() {
        return configService.get(app);
    }

    @Override
    public String get(String configKey) {
        return configService.get(app, configKey);
    }

    @Override
    public void watch(ConfigWatcher configWatcher) {
        configService.watch(app, configWatcher);
    }

    @Accessors(chain = true)
    @Setter
    public static class Builder {

        private App app;
        private CuratorFramework curatorFramework;

        public DefaultConfigClient build() {
            Preconditions.checkNotNull(app);
            Preconditions.checkNotNull(curatorFramework);
            DefaultConfigService defaultConfigService = new DefaultConfigService.Builder()
                    .setCuratorFramework(curatorFramework).build();
            DefaultConfigClient defaultConfigClient = new DefaultConfigClient();
            defaultConfigClient.setApp(app);
            defaultConfigClient.setConfigService(defaultConfigService);
            return defaultConfigClient;
        }
    }
}
