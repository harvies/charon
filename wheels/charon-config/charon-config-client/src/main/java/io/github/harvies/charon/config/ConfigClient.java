package io.github.harvies.charon.config;

import java.util.Properties;

public interface ConfigClient {

    App getApp();

    Properties get();

    String get(String configKey);

    void watch(ConfigWatcher configWatcher);
}
