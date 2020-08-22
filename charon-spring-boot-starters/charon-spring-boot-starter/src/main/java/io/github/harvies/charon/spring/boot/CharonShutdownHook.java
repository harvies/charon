package io.github.harvies.charon.spring.boot;

import io.github.harvies.charon.spring.boot.event.CharonBootShutdownEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationEventPublisher;

import javax.annotation.Resource;

@Slf4j
public class CharonShutdownHook implements InitializingBean {
    @Resource
    private CharonBootApplicationProperties properties;
    @Resource
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void afterPropertiesSet() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.error("charon boot application shutdown:{}", properties);
            applicationEventPublisher.publishEvent(new CharonBootShutdownEvent(properties));
        }));
    }

}
