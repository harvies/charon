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

package io.github.harvies.charon.ribbon;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.client.naming.utils.CollectionUtils;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractServerList;
import io.github.harvies.charon.util.RequestTag;

/**
 * @author xiaojing
 * @author renhaojun
 */
public class CharonNacosServerList extends AbstractServerList<NacosServer> {

    private NacosDiscoveryProperties discoveryProperties;

    private String serviceId;

    public CharonNacosServerList(NacosDiscoveryProperties discoveryProperties) {
        this.discoveryProperties = discoveryProperties;
    }

    @Override
    public List<NacosServer> getInitialListOfServers() {
        return getServers();
    }

    @Override
    public List<NacosServer> getUpdatedListOfServers() {
        RequestTag.remove();
        return getServers();
    }

    private List<NacosServer> getServers() {
        try {
            List<Instance> list = new ArrayList<>();
            // TODO: 2021/7/18 读取配置中心
            //greenGroup
            String greenGroup = "harvies";
            List<Instance> greenInstances = discoveryProperties.namingServiceInstance()
                    .selectInstances(serviceId, greenGroup, true);
            if (!CollectionUtils.isEmpty(greenInstances)) {
                list.addAll(greenInstances);
            }

            String group = discoveryProperties.getGroup();
            List<Instance> instances = discoveryProperties.namingServiceInstance()
                    .selectInstances(serviceId, group, true);
            list.addAll(instances);
            return instancesToServerList(list);
        } catch (Exception e) {
            throw new IllegalStateException(
                    "Can not get service instances from nacos, serviceId=" + serviceId,
                    e);
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
