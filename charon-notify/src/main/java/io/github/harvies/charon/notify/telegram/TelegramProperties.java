package io.github.harvies.charon.notify.telegram;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import static io.github.harvies.charon.notify.Constants.NOTIFY_PROPERTIES_PREFIX;

/**
 * server酱配置
 */
@Getter
@Setter
@ConfigurationProperties(prefix = TelegramProperties.TELEGRAM_PROPERTIES_PREFIX)
public class TelegramProperties {

    /**
     * telegram
     */
    public static final String TELEGRAM = "telegram";
    /**
     * telegram
     */
    public static final String TELEGRAM_PROPERTIES_PREFIX = NOTIFY_PROPERTIES_PREFIX + "." + TELEGRAM;

    /**
     * chatId
     */
    private String chatId;
    /**
     * botToken
     */
    private String botToken;
}