package io.github.harvies.charon.config;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ConfigData {

    private String key;

    private String value;
}
