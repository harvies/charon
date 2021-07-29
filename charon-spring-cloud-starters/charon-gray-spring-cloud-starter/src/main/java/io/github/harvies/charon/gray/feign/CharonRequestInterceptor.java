package io.github.harvies.charon.gray.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import io.github.harvies.charon.gray.PriorityRouteGroup;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CharonRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        //将webflux中的header中的请求标识放入feign header传递到下一个服务
        template.header(PriorityRouteGroup.getTagName(), PriorityRouteGroup.get());
    }
}