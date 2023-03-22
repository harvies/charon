package io.github.harvies.charon.nacos.discovery;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.NacosServiceManager;
import com.alibaba.cloud.nacos.discovery.NacosDiscoveryAutoConfiguration;
import com.alibaba.nacos.api.naming.NamingService;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.Resource;

@Configuration(proxyBeanMethods = false)
@AutoConfigureAfter({NacosDiscoveryAutoConfiguration.class})
public class CharonNacosDiscoveryAutoConfiguration {

    @Resource
    private NacosServiceManager nacosServiceManager;
    @Resource
    private NacosDiscoveryProperties nacosDiscoveryProperties;

    @Bean
    public NamingService namingService() {
        return nacosServiceManager.getNamingService(nacosDiscoveryProperties.getNacosProperties());
    }
}
