package io.github.harvies.charon.spring.boot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
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
        log.info("收到事件:[{}]", event.getClass());
        if (event instanceof ApplicationStartedEvent) {
            log.info("应用启动完成!");
            ConfigurableApplicationContext applicationContext = ((ApplicationStartedEvent) event).getApplicationContext();
            SpringContextUtils.setApplicationContext(applicationContext);
        }

    }
}
