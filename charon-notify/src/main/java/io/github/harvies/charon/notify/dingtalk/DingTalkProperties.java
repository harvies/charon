package io.github.harvies.charon.notify.dingtalk;

import io.github.harvies.charon.notify.Constants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * github配置
 */
@Getter
@Setter
@ConfigurationProperties(prefix = DingTalkProperties.DING_TALK_PROPERTIES_PREFIX)
public class DingTalkProperties {

    /**
     * 钉钉配置前缀
     */
    public static final String DING_TALK_PROPERTIES_PREFIX = Constants.NOTIFY_PROPERTIES_PREFIX + "." + DingTalkProperties.DING_TALK;

    /**
     * 钉钉
     */
    public static final String DING_TALK = "ding-talk";

    /**
     * webHook地址
     */
    private String webHookUrl;
}