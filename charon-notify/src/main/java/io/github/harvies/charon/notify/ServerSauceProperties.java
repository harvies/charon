package io.github.harvies.charon.notify;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * server酱配置
 */
@Getter
@Setter
@ConfigurationProperties(prefix = Constants.SERVER_SAUCE_PROPERTIES_PREFIX)
public class ServerSauceProperties {
    /**
     * webHook地址
     */
    private String webHookUrl;

}