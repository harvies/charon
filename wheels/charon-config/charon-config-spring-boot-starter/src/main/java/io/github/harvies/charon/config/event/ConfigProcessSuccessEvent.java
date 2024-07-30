package io.github.harvies.charon.config.event;

import org.springframework.context.ApplicationEvent;

/**
 * 配置处理成功事件
 */
public class ConfigProcessSuccessEvent extends ApplicationEvent {

    /**
     * Create a new {@code ApplicationEvent}.
     * @param source the object on which the event initially occurred or with
     * which the event is associated (never {@code null})
     */
    public ConfigProcessSuccessEvent(Object source) {
        super(source);
    }
}
