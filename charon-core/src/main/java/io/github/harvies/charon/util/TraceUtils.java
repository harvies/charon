package io.github.harvies.charon.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;

import java.util.Objects;

public class TraceUtils {

    private static ThreadLocal<String> localTraceId = new ThreadLocal<>();

    public static String getTraceId() {
        String string = localTraceId.get();
        if (StringUtils.isBlank(string)) {
            String traceId = TraceContext.traceId();
            if (StringUtils.isBlank(traceId) || Objects.equals("Ignored_Trace", traceId)) {
                traceId = RandomUtils.uuid();
            }
            localTraceId.set(traceId);
            return traceId;
        }
        return string;
    }

    public static void removeTraceId() {
        localTraceId.remove();
    }
}
