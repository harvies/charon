package io.github.harvies.charon.config;

import java.util.Properties;

public interface ConfigService {

    Properties get(App app);

    String get(App app, String configKey);

    void update(App app, ConfigData configData);

    void delete(App app, String configKey);

    void deleteAll(App app);

    void watch(App app, ConfigWatcher configWatcher);
}
