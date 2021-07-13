package io.github.harvies.charon.ribbon;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerListFilter;
import org.springframework.cloud.netflix.ribbon.ZonePreferenceServerListFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class RibbonServerListFilterConfiguration {

    // TODO: 2021/7/14 IClientConfig iClientConfig
    @Bean
    public ServerListFilter<Server> ribbonServerListFilter() {
        CharonServerListFilter filter = new CharonServerListFilter();
//        filter.initWithNiwsConfig(iClientConfig);
        return filter;
    }
}
