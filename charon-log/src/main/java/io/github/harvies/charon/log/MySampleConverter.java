package io.github.harvies.charon.log;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import io.github.harvies.charon.json.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.helpers.MessageFormatter;

@Slf4j
public class MySampleConverter extends ClassicConverter {

    @Override
    public String convert(ILoggingEvent event) {
        log.info("aaaaaaaaaaa");

        Object[] argumentArray = event.getArgumentArray();
        for (int i = 0; i < argumentArray.length; i++) {
            argumentArray[i] = JsonUtils.toJSONString(argumentArray[i]);
        }
        if (event.getArgumentArray() != null) {
            return MessageFormatter.arrayFormat(event.getMessage(), event.getArgumentArray()).getMessage();
        } else {
            return event.getFormattedMessage();
        }
//        return event.getFormattedMessage();
    }
}
