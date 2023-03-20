package io.github.harvies.charon.spring.boot.core;

import io.github.harvies.charon.spring.boot.core.event.CharonBootShutdownEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

@Slf4j
public class CharonShutdownHook implements InitializingBean {
    @Autowired
    private CharonBootApplicationProperties charonBootApplicationProperties;
    @Autowired
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
