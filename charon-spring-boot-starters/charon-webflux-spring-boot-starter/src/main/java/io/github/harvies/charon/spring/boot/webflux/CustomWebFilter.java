package io.github.harvies.charon.spring.boot.webflux;

import io.github.harvies.charon.util.RequestTag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

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