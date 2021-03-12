package io.github.harvies.charon.dubbo.listener.event;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.config.event.ServiceConfigExportedEvent;
import org.apache.dubbo.event.EventListener;

import java.util.List;

/**
 * dubbo服务提供者暴露监听器
 */
@Slf4j
public class ServiceConfigExportedEventListener implements EventListener<ServiceConfigExportedEvent> {
    @Override
    public void onEvent(ServiceConfigExportedEvent event) {
        List<URL> exportedUrls = event.getServiceConfig().getExportedUrls();
        log.info("ServiceConfigExportedEvent exportedUrls:[{}]", exportedUrls);
    }
}
