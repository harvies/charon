package io.github.harvies.charon.gray;

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
        PriorityRouteGroup.set(serverWebExchange.getRequest().getHeaders().getFirst(PriorityRouteGroup.getTagName()));
        log.info("save priorityRouteGroup [{}]", PriorityRouteGroup.get());
        return webFilterChain.filter(serverWebExchange);
    }
}