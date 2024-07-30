package io.github.harvies.charon.config;

import io.github.harvies.charon.config.event.ConfigEvent;

public interface ConfigWatcher {

    void process(ConfigEvent configEvent);
}
