package io.github.harvies.charon.dubbo.listener.event;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.config.event.ReferenceConfigInitializedEvent;
import org.apache.dubbo.event.EventListener;

/**
 * dubbo消费者初始化监听器
 */
@Slf4j
public class ReferenceConfigInitializedEventListener implements EventListener<ReferenceConfigInitializedEvent> {

    @Override
    public void onEvent(ReferenceConfigInitializedEvent event) {
        URL url = event.getInvoker().getUrl();
        log.info("ReferenceConfigInitializedEvent url:[{}]", url);
    }
}
