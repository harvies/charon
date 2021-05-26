package io.github.harvies.charon.notify;

import io.github.harvies.charon.notify.dingtalk.DingTalkNotifyProvider;
import io.github.harvies.charon.notify.dingtalk.DingTalkProperties;
import io.github.harvies.charon.notify.serversauce.ServerSauceNotifyProvider;
import io.github.harvies.charon.notify.serversauce.ServerSauceProperties;
import io.github.harvies.charon.notify.telegram.TelegramProperties;
import io.github.harvies.charon.notify.telegram.TelegramProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.DefaultBotOptions;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({DingTalkProperties.class, ServerSauceProperties.class, TelegramProperties.class})
public class NotifyAutoConfiguration {

    static final String PREFERRED_NOTIFY_PROPERTY = Constants.NOTIFY_PROPERTIES_PREFIX + ".preferred";

    @Value("${charon.proxy.socks5.host:}")
    private String proxyHost;

    @Value("${charon.proxy.socks5.port:}")
    private int proxyPort;

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(name = PREFERRED_NOTIFY_PROPERTY, havingValue = DingTalkProperties.DING_TALK, matchIfMissing = true)
    NotifyProvider dingTalkNotifyProvider(DingTalkProperties dingTalkProperties) {
        return new DingTalkNotifyProvider(dingTalkProperties);
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(name = PREFERRED_NOTIFY_PROPERTY, havingValue = ServerSauceProperties.SERVER_SAUCE)
    NotifyProvider serverSauceNotifyProvider(ServerSauceProperties serverSauceProperties) {
        return new ServerSauceNotifyProvider(serverSauceProperties);
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(name = PREFERRED_NOTIFY_PROPERTY, havingValue = TelegramProperties.TELEGRAM)
    NotifyProvider telegramNotifyProvider(TelegramProperties telegramProperties) {
        DefaultBotOptions botOptions = new DefaultBotOptions();
        botOptions.setProxyHost(proxyHost);
        botOptions.setProxyPort(proxyPort);
        botOptions.setProxyType(DefaultBotOptions.ProxyType.SOCKS5);
        return new TelegramProvider(botOptions, telegramProperties);
    }
}
