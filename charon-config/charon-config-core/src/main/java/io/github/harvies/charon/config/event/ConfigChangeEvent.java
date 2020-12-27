package io.github.harvies.charon.config.event;

import lombok.Data;

@Data
public class ConfigChangeEvent implements ConfigEvent {
    
    private String node;

    public ConfigChangeEvent(String node) {
        this.node = node;
    }
}
