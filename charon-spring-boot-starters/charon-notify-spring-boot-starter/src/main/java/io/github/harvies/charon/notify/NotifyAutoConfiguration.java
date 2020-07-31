package io.github.harvies.charon.notify;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(DingTalkProperties.class)
public class NotifyAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    NotifyService dingTalkNotify(DingTalkProperties dingTalkProperties) {
        return new DingTalkNotifyService(dingTalkProperties);
    }
}
