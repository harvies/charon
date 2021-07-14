package io.github.harvies.charon.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;

import java.util.Objects;

public class TraceUtils {

    private static final ThreadLocal<String> LOCAL_TRACE_ID = new ThreadLocal<>();

    public static String getTraceId() {
        String string = LOCAL_TRACE_ID.get();
        if (StringUtils.isBlank(string)) {
            String traceId = TraceContext.traceId();
            if (StringUtils.isBlank(traceId) || Objects.equals("Ignored_Trace", traceId)) {
                traceId = RandomUtils.uuid().replaceAll("-", "");
            }
            LOCAL_TRACE_ID.set(traceId);
            return traceId;
        }
        return string;
    }

    public static void removeTraceId() {
        LOCAL_TRACE_ID.remove();
    }
}
