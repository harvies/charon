/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.harvies.charon.gray;

import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.ribbon.ConditionalOnRibbonNacos;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.naming.NamingService;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.ServerList;
import org.springframework.cloud.netflix.ribbon.PropertiesFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * integrated Ribbon by default.
 *
 * @author xiaojing
 * @author liujunjie
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnRibbonNacos
public class CharonNacosRibbonClientConfiguration {

    @Resource
    private PropertiesFactory propertiesFactory;

    @Resource
    private NacosDiscoveryProperties nacosDiscoveryProperties;

    @Resource
    private NacosConfigProperties nacosConfigProperties;

    @Resource
    private IClientConfig clientConfig;

    @Resource
    private NamingService namingService;

    @Resource
    private ConfigService configService;


    @Bean
    public IRule ribbonRule() {
        CharonRule rule = new CharonRule();
        rule.initWithNiwsConfig(clientConfig);
        return rule;
    }


    @Bean
    public ServerList<?> ribbonServerList() {
        if (this.propertiesFactory.isSet(ServerList.class, clientConfig.getClientName())) {
            ServerList serverList = this.propertiesFactory.get(ServerList.class, clientConfig,
                    clientConfig.getClientName());
            return serverList;
        }
        CharonNacosServerList serverList = new CharonNacosServerList(namingService, configService, nacosConfigProperties, nacosDiscoveryProperties);
        serverList.initWithNiwsConfig(clientConfig);
        return serverList;
    }
}
