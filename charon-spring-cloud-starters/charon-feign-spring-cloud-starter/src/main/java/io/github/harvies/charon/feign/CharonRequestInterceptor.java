package io.github.harvies.charon.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import io.github.harvies.charon.util.RequestTag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CharonRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        template.header(RequestTag.getTagName(), RequestTag.get());
    }
}
