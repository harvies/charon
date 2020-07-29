package io.github.harvies.charon.spring.boot.notify;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "charon.notify")
@Getter
@Setter
public class NotifyProperties {

    /**
     * 钉钉webHook地址
     */
    private String dingTalkWebHookUrl;

}
