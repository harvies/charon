//package io.github.harvies.charon.log;
//
//import ch.qos.logback.classic.pattern.ClassicConverter;
//import ch.qos.logback.classic.spi.ILoggingEvent;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.builder.ToStringBuilder;
//import org.apache.commons.lang3.builder.ToStringStyle;
//import org.slf4j.helpers.MessageFormatter;
//
///**
// * 参数转换
// */
//@Slf4j
//public class ArgumentToStringConverter extends ClassicConverter {
//
//    @Override
//    public String convert(ILoggingEvent event) {
//        log.debug("convert begin");
//        Object[] argumentArray = event.getArgumentArray();
//        for (int i = 0; i < argumentArray.length; i++) {
//            argumentArray[i] = ToStringBuilder.reflectionToString(argumentArray[i], ToStringStyle.JSON_STYLE);
//        }
//        if (event.getArgumentArray() != null) {
//            return MessageFormatter.arrayFormat(event.getMessage(), event.getArgumentArray()).getMessage();
//        } else {
//            return event.getFormattedMessage();
//        }
//    }
//}
