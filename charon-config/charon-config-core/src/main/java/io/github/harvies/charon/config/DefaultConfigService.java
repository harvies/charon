package io.github.harvies.charon.config;

import lombok.AccessLevel;
import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
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
            @Cleanup ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            @Cleanup InputStreamReader inputStreamReader = new InputStreamReader(byteArrayInputStream, StandardCharsets.UTF_8);
            properties.load(inputStreamReader);
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
    public void watch(App app, ConfigWatcher configWatcher) {
        DefaultConfigWatcher defaultConfigWatcher = new DefaultConfigWatcher(configWatcher);
        try {
            curatorFramework.watchers().add().usingWatcher(defaultConfigWatcher).forPath(app.key());
        } catch (Exception e) {
            log.warn("watch exception app:[{}]", app, e);
        }
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
