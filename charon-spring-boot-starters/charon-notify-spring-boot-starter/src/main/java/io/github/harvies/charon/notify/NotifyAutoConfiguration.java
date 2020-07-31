package io.github.harvies.charon.notify;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({DingTalkProperties.class, ServerSauceProperties.class})
public class NotifyAutoConfiguration {

    static final String PREFERRED_NOTIFY_PROPERTY = Constants.NOTIFY_PROPERTIES_PREFIX + ".preferred";

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(name = PREFERRED_NOTIFY_PROPERTY, havingValue = Constants.DING_TALK, matchIfMissing = true)
    NotifyService dingTalkNotifyService(DingTalkProperties dingTalkProperties) {
        return new DingTalkNotifyService(dingTalkProperties);
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(name = PREFERRED_NOTIFY_PROPERTY, havingValue = Constants.SERVER_SAUCE)
    NotifyService serverSauceNotifyService(ServerSauceProperties serverSauceProperties) {
        return new ServerSauceNotifyService(serverSauceProperties);
    }
}
