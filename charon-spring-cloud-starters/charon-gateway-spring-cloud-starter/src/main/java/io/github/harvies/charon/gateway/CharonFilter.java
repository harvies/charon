///*
// * Copyright 2013-2020 the original author or authors.
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      https://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//package io.github.harvies.charon.gateway;
//
//import com.alibaba.fastjson.JSONArray;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//@Slf4j
//public class CharonFilter implements GlobalFilter, Ordered {
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        log.info("request = {}", JSONArray.toJSONString(exchange.getRequest()));
//        String token = exchange.getRequest().getQueryParams().getFirst("authToken");
//        //向headers中放文件，记得build
//        ServerHttpRequest host = exchange.getRequest().mutate().header("a", "888").build();
//        //将现在的request 变成 change对象
//        ServerWebExchange build = exchange.mutate().request(host).build();
//        return chain.filter(build);
//
//    }
//
//    @Override
//    public int getOrder() {
//        return 0;
//    }
//
//}
