package io.github.harvies.charon.spring.boot.event;

import io.github.harvies.charon.spring.boot.CharonBootApplicationProperties;
import org.springframework.context.ApplicationEvent;

public class CharonBootShutdownEvent extends ApplicationEvent {

    public CharonBootShutdownEvent(CharonBootApplicationProperties properties) {
        super(properties);
    }
}