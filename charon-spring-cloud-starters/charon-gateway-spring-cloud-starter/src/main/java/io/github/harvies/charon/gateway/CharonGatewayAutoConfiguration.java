package io.github.harvies.charon.gateway;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.gateway.config.GatewayAutoConfiguration;
import org.springframework.cloud.gateway.config.conditional.ConditionalOnEnabledGlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(name = "spring.cloud.gateway.enabled", matchIfMissing = true)
@AutoConfigureBefore(GatewayAutoConfiguration.class)
public class CharonGatewayAutoConfiguration {

    @Bean
    public PersistenceRouteDefinitionRepository persistenceRouteDefinitionRepository() {
        return new PersistenceRouteDefinitionRepository();
    }
}
