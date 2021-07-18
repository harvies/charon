package io.github.harvies.charon.gateway;

import io.github.harvies.charon.util.RequestTag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

// TODO: 2021/7/18 提取出去
@Slf4j
public class CustomWebFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {
        //将header中标记放入ThreadLocal
        RequestTag.set(serverWebExchange.getRequest().getHeaders().getFirst(RequestTag.getTagName()));
        log.info("save requestTag tag:[{}]", RequestTag.get());
        return webFilterChain.filter(serverWebExchange);
    }
}