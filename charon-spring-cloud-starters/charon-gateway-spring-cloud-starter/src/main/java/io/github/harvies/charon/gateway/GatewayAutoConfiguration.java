package io.github.harvies.charon.gateway;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.gateway.config.conditional.ConditionalOnEnabledGlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(name = "spring.cloud.gateway.enabled", matchIfMissing = true)
public class GatewayAutoConfiguration {

    @Bean
    @ConditionalOnEnabledGlobalFilter
    public CharonFilter charonFilter() {
        return new CharonFilter();
    }
}
