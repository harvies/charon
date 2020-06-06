//package io.github.harvies.charon.core.utils;
//
//import ch.qos.logback.classic.spi.ILoggingEvent;
//import ch.qos.logback.core.filter.Filter;
//import ch.qos.logback.core.spi.FilterReply;
//import org.apache.commons.lang3.builder.ToStringBuilder;
//import org.apache.commons.lang3.builder.ToStringStyle;
//
//public class CustomerFilter extends Filter<ILoggingEvent> {
//    @Override
//    public FilterReply decide(ILoggingEvent event) {
//        if (event.getLoggerName().startsWith("io.github.harvies")) {
//            Object[] params = event.getArgumentArray();
//            for (int index = 0; index < params.length; index++) {
//                Object param = params[index];
//                if (!param.getClass().isPrimitive()) {
////                    params[index] = ToStringBuilder.reflectionToString(param, ToStringStyle.JSON_STYLE);
//                }
//            }
//        }
//        return FilterReply.ACCEPT;
//    }
//}