package io.github.harvies.charon.spring.boot.notify;

import io.github.harvies.charon.notify.DingTalkNotify;
import io.github.harvies.charon.notify.Notify;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(NotifyProperties.class)
public class NotifyAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    Notify dingTalkNotify(NotifyProperties notifyProperties) {
        return new DingTalkNotify(notifyProperties.getDingTalkWebHookUrl());
    }
}
