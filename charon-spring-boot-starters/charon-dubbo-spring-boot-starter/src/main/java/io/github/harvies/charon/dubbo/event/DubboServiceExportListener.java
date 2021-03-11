package io.github.harvies.charon.dubbo.event;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.context.event.ServiceBeanExportedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * dubbo服务暴露监听
 */
@Component
@Slf4j
public class DubboServiceExportListener implements ApplicationListener<ServiceBeanExportedEvent> {

    @Override
    public void onApplicationEvent(ServiceBeanExportedEvent event) {
        log.info("dubbo exported Urls: [{}]", event.getServiceBean().getExportedUrls());
    }
}
