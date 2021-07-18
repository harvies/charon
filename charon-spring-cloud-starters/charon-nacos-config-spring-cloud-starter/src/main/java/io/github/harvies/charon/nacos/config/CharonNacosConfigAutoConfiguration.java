package io.github.harvies.charon.nacos.config;

import com.alibaba.cloud.nacos.NacosConfigAutoConfiguration;
import com.alibaba.cloud.nacos.NacosConfigBootstrapConfiguration;
import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.config.ConfigService;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration(proxyBeanMethods = false)
@AutoConfigureAfter({NacosConfigAutoConfiguration.class, NacosConfigBootstrapConfiguration.class})
public class CharonNacosConfigAutoConfiguration {

    @Resource
    private NacosConfigManager nacosConfigManager;

    @Bean
    public ConfigService configService() {
        return nacosConfigManager.getConfigService();
    }
}
