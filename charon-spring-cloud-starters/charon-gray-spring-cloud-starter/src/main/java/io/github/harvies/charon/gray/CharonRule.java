/*
 *
 * Copyright 2013 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.github.harvies.charon.gray;

import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * A loadbalacing strategy that randomly distributes traffic amongst existing
 * servers.
 *
 * @author stonse
 */
@Slf4j
public class CharonRule extends AbstractLoadBalancerRule {

    private Server priorityRouteGroupDeal(ILoadBalancer lb) {
        String priorityRouteGroup = PriorityRouteGroup.get();
        log.info("priorityRouteGroupDeal priorityRouteGroup:[{}]", priorityRouteGroup);
        if (StringUtils.isBlank(priorityRouteGroup)) {
            return null;
        }
        List<Server> collect = lb.getReachableServers().stream().filter(server -> Objects.equals(priorityRouteGroup, StringUtils.split(((NacosServer) server).getInstance().getServiceName(), "@@")[0])).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(collect)) {
            return null;
        }
        return collect.get(RandomUtils.nextInt(0, collect.size()));
    }

    /**
     * Randomly choose from all living servers
     */
    public Server choose(ILoadBalancer lb, Object key) {

        Server requestTagDealResult = priorityRouteGroupDeal(lb);
        if (requestTagDealResult != null) {
            return requestTagDealResult;
        }

        if (lb == null) {
            return null;
        }
        Server server = null;

        while (server == null) {
            if (Thread.interrupted()) {
                return null;
            }
            // TODO: 2021/7/28 取默认服务链路版本号
            List<Server> upList = lb.getReachableServers().stream().filter(server1 -> Objects.equals(StringUtils.split(((NacosServer) server1).getInstance().getServiceName(), "@@")[0], "1.0.0")).collect(Collectors.toList());
            List<Server> allList = lb.getAllServers().stream().filter(server1 -> Objects.equals(StringUtils.split(((NacosServer) server1).getInstance().getServiceName(), "@@")[0], "1.0.0")).collect(Collectors.toList());

            int serverCount = allList.size();
            if (serverCount == 0) {
                /*
                 * No servers. End regardless of pass, because subsequent passes
                 * only get more restrictive.
                 */
                return null;
            }

            int index = chooseRandomInt(serverCount);
            server = upList.get(index);

            if (server == null) {
                /*
                 * The only time this should happen is if the server list were
                 * somehow trimmed. This is a transient condition. Retry after
                 * yielding.
                 */
                Thread.yield();
                continue;
            }

            if (server.isAlive()) {
                return (server);
            }

            // Shouldn't actually happen.. but must be transient or a bug.
            server = null;
            Thread.yield();
        }

        return server;

    }

    protected int chooseRandomInt(int serverCount) {
        return ThreadLocalRandom.current().nextInt(serverCount);
    }

    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {
        // TODO Auto-generated method stub

    }
}
