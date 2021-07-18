package io.github.harvies.charon.feign;

import io.github.harvies.charon.util.RequestTag;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

public class CustomWebFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {
        RequestTag.set(serverWebExchange.getRequest().getHeaders().getFirst(RequestTag.getTagName()));
        return webFilterChain.filter(serverWebExchange);
    }
}