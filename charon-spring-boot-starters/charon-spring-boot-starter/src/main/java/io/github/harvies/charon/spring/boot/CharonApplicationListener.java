package io.github.harvies.charon.spring.boot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Spring启动完成监听器
 */
@Slf4j
public class CharonApplicationListener implements ApplicationListener<ApplicationEvent> {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        log.debug("Charon Spring Boot收到事件:[{}]", event);
        if (event instanceof ApplicationReadyEvent) {
            log.info("Spring容器启动完成!");
            ConfigurableApplicationContext applicationContext = ((ApplicationReadyEvent) event).getApplicationContext();
            SpringContextUtils.setApplicationContext(applicationContext);
        }

    }
}
