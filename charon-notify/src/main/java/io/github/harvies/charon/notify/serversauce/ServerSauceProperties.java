package io.github.harvies.charon.notify.serversauce;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import static io.github.harvies.charon.notify.Constants.NOTIFY_PROPERTIES_PREFIX;

/**
 * server酱配置
 */
@Getter
@Setter
@ConfigurationProperties(prefix = ServerSauceProperties.SERVER_SAUCE_PROPERTIES_PREFIX)
public class ServerSauceProperties {

    /**
     * server酱
     */
    public static final String SERVER_SAUCE = "server-sauce";
    /**
     * server酱配置前缀
     */
    public static final String SERVER_SAUCE_PROPERTIES_PREFIX = NOTIFY_PROPERTIES_PREFIX + "." + SERVER_SAUCE;

    /**
     * webHook地址
     */
    private String webHookUrl;
}