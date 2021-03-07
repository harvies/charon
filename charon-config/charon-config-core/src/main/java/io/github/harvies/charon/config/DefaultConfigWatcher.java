package io.github.harvies.charon.config;

import io.github.harvies.charon.config.event.ConfigChangeEvent;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.zookeeper.WatchedEvent;

public class DefaultConfigWatcher implements CuratorWatcher {

    private final ConfigWatcher configWatcher;

    public DefaultConfigWatcher(ConfigWatcher configWatcher) {
        this.configWatcher = configWatcher;
    }

    @Override
    public void process(WatchedEvent event) {
        configWatcher.process(new ConfigChangeEvent(event.getPath()));
    }
}
