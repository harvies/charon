package io.github.harvies.charon.config;

import org.apache.curator.framework.api.CuratorWatcher;

import java.util.Map;
import java.util.Properties;

public interface ConfigService {

    Properties get(App app);

    String get(App app, String configKey);

    Map<String, String> update(App app, ConfigData configData);

    Map<String, String> delete(App app, String configKey);

    Map<String, String> deleteAll(App app);

    void watch(App app, CuratorWatcher curatorWatcher);
}
