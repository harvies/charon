package io.github.harvies.charon.config;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class App {
    private EnvEnum env;
    private String appName;

    public String key() {
        return "/charon/configs/" + env.getCode() + "/" + appName;
    }
}
