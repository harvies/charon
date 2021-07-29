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
import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.client.naming.utils.CollectionUtils;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractServerList;
import io.github.harvies.charon.gray.constant.CommonConstant;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author harvies
 */
public class CharonNacosServerList extends AbstractServerList<NacosServer> {

    private NacosConfigProperties nacosConfigProperties;

    private NacosDiscoveryProperties discoveryProperties;

    private NamingService namingService;

    private ConfigService configService;

    private String serviceId;

    public CharonNacosServerList(NamingService namingService, ConfigService configService, NacosConfigProperties nacosConfigProperties, NacosDiscoveryProperties discoveryProperties) {
        this.namingService = namingService;
        this.configService = configService;
        this.nacosConfigProperties = nacosConfigProperties;
        this.discoveryProperties = discoveryProperties;
    }

    @Override
    public List<NacosServer> getInitialListOfServers() {
        return getServers();
    }

    @Override
    public List<NacosServer> getUpdatedListOfServers() {
        return getServers();
    }

    private List<NacosServer> getServers() {
        try {
            List<Instance> list = new ArrayList<>();
            fillGreenGroup(list);
            List<Instance> instances = namingService.selectInstances(serviceId, CommonConstant.VERSION, true);
            list.addAll(instances);
            return instancesToServerList(list);
        } catch (Exception e) {
            throw new IllegalStateException(
                    "Can not get service instances from nacos, serviceId=" + serviceId,
                    e);
        }
    }

    /**
     * 将配置的绿色组服务已拉取过来
     *
     * @param list
     * @throws NacosException
     */
    private void fillGreenGroup(List<Instance> list) throws NacosException {
        String greenGroupStr = configService.getConfig("green-group", nacosConfigProperties.getGroup(), 5000);
        if (StringUtils.isNotBlank(greenGroupStr)) {
            String[] greenGroupArr = StringUtils.split(greenGroupStr, ",");
            for (String greenGroup : greenGroupArr) {
                if (Objects.equals(greenGroup, CommonConstant.VERSION)) {
                    continue;
                }
                List<Instance> greenInstances = namingService.selectInstances(serviceId, greenGroup, true);
                if (!CollectionUtils.isEmpty(greenInstances)) {
                    list.addAll(greenInstances);
                }
            }
        }
    }

    private List<NacosServer> instancesToServerList(List<Instance> instances) {
        List<NacosServer> result = new ArrayList<>();
        if (CollectionUtils.isEmpty(instances)) {
            return result;
        }
        for (Instance instance : instances) {
            result.add(new NacosServer(instance));
        }

        return result;
    }

    public String getServiceId() {
        return serviceId;
    }

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {
        this.serviceId = iClientConfig.getClientName();
    }

}
