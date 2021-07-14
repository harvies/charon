///*
// *
// * Copyright 2013 Netflix, Inc.
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// * http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// *
// */
//package io.github.harvies.charon.ribbon;
//
//import com.netflix.client.config.IClientConfig;
//import com.netflix.loadbalancer.AbstractLoadBalancerRule;
//import com.netflix.loadbalancer.ILoadBalancer;
//import com.netflix.loadbalancer.Server;
//import io.github.harvies.charon.util.RequestTag;
//import org.apache.commons.lang3.StringUtils;
//
//import java.util.List;
//import java.util.Objects;
//import java.util.concurrent.ThreadLocalRandom;
//
///**
// * A loadbalacing strategy that randomly distributes traffic amongst existing
// * servers.
// *
// * @author stonse
// */
//public class CharonRule extends AbstractLoadBalancerRule {
//
//    private Server requestTagDeal(ILoadBalancer lb) {
//        String requestTag = RequestTag.get();
//        if (StringUtils.isBlank(requestTag)) {
//            return null;
//        }
//        for (Server reachableServer : lb.getReachableServers()) {
////            if (reachableServer instanceof NacosServer) {
////                String tag = ((NacosServer) reachableServer).getMetadata().get(RequestTag.getTag());
////                if (Objects.equals(requestTag, tag)) {
////                    return reachableServer;
////                }
////            }
//        }
//        return null;
//    }
//
//    /**
//     * Randomly choose from all living servers
//     */
//    public Server choose(ILoadBalancer lb, Object key) {
//
//        Server requestTagDealResult = requestTagDeal(lb);
//        if (requestTagDealResult != null) {
//            return requestTagDealResult;
//        }
//
//        if (lb == null) {
//            return null;
//        }
//        Server server = null;
//
//        while (server == null) {
//            if (Thread.interrupted()) {
//                return null;
//            }
//            List<Server> upList = lb.getReachableServers();
//            List<Server> allList = lb.getAllServers();
//
//            int serverCount = allList.size();
//            if (serverCount == 0) {
//                /*
//                 * No servers. End regardless of pass, because subsequent passes
//                 * only get more restrictive.
//                 */
//                return null;
//            }
//
//            int index = chooseRandomInt(serverCount);
//            server = upList.get(index);
//
//            if (server == null) {
//                /*
//                 * The only time this should happen is if the server list were
//                 * somehow trimmed. This is a transient condition. Retry after
//                 * yielding.
//                 */
//                Thread.yield();
//                continue;
//            }
//
//            if (server.isAlive()) {
//                return (server);
//            }
//
//            // Shouldn't actually happen.. but must be transient or a bug.
//            server = null;
//            Thread.yield();
//        }
//
//        return server;
//
//    }
//
//    protected int chooseRandomInt(int serverCount) {
//        return ThreadLocalRandom.current().nextInt(serverCount);
//    }
//
//    @Override
//    public Server choose(Object key) {
//        return choose(getLoadBalancer(), key);
//    }
//
//    @Override
//    public void initWithNiwsConfig(IClientConfig clientConfig) {
//        // TODO Auto-generated method stub
//
//    }
//}
