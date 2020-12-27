package io.github.harvies.charon.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.CuratorWatcher;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DefaultConfigService implements ConfigService {

    @Setter
    private CuratorFramework curatorFramework;

    @Override
    public Properties get(App app) {
        Properties properties = new Properties();
        byte[] bytes;
        try {
            bytes = curatorFramework.getData().forPath(app.key());
        } catch (Exception e) {
            log.warn("node not exists!");
            return properties;
        }
        try {
            properties.load(new ByteArrayInputStream(bytes));
        } catch (IOException e) {
            log.warn("config invalid!");
        }
        return properties;
    }

    @Override
    public String get(App app, String configKey) {
        return get(app).getProperty(configKey);
    }

    @Override
    public Map<String, String> update(App app, ConfigData configData) {
        return null;
    }

    @Override
    public Map<String, String> delete(App app, String configKey) {
        return null;
    }

    @Override
    public Map<String, String> deleteAll(App app) {
        return null;
    }

    @Override
    public void watch(App app, CuratorWatcher curatorWatcher) {

    }

    public static class Builder {
        @Setter
        @Accessors(chain = true)
        private CuratorFramework curatorFramework;

        public DefaultConfigService build() {
            DefaultConfigService defaultConfigService = new DefaultConfigService();
            defaultConfigService.setCuratorFramework(curatorFramework);
            return defaultConfigService;
        }
    }

}
