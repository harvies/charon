package io.github.harvies.charon.config;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
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
            bytes = curatorFramework.getData().forPath(app.path());
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

    @SneakyThrows
    @Override
    public void update(App app, ConfigData configData) {
        Properties properties = get(app);
        properties.put(configData.getKey(), configData.getValue());
        writePropertiesToZk(app, properties);
    }

    @SneakyThrows
    @Override
    public void delete(App app, String configKey) {
        Properties properties = get(app);
        properties.remove(configKey);
        writePropertiesToZk(app, properties);
    }

    private void writePropertiesToZk(App app, Properties properties) throws Exception {
        @Cleanup ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        properties.store(byteArrayOutputStream, null);
        curatorFramework.create().orSetData().forPath(app.path(), byteArrayOutputStream.toByteArray());
    }

    @SneakyThrows
    @Override
    public void deleteAll(App app) {
        curatorFramework.delete().forPath(app.path());
    }

    @Override
    public void watch(App app, ConfigWatcher configWatcher) {
        DefaultConfigWatcher defaultConfigWatcher = new DefaultConfigWatcher(configWatcher);
        try {
            curatorFramework.watchers().add().usingWatcher(defaultConfigWatcher).forPath(app.path());
        } catch (Exception e) {
            log.warn("watch exception app:[{}]", app, e);
        }
    }

    @Setter
    @Accessors(chain = true)
    protected static class Builder {

        private CuratorFramework curatorFramework;

        public DefaultConfigService build() {
            DefaultConfigService defaultConfigService = new DefaultConfigService();
            defaultConfigService.setCuratorFramework(curatorFramework);
            return defaultConfigService;
        }
    }
}
