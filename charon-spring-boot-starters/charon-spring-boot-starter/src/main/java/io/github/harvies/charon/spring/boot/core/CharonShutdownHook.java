package io.github.harvies.charon.spring.boot.core;

import io.github.harvies.charon.spring.boot.core.event.CharonBootShutdownEvent;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationEventPublisher;
@Slf4j
public class CharonShutdownHook implements InitializingBean {
    @Resource
    private CharonBootApplicationProperties charonBootApplicationProperties;
    @Resource
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void afterPropertiesSet() {
        log.info("charon boot application register shutdown hook success!");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.error("charon boot application shutdown:{}", charonBootApplicationProperties);
            applicationEventPublisher.publishEvent(new CharonBootShutdownEvent(charonBootApplicationProperties));
        }));
    }

}
