package io.github.harvies.charon.dubbo.filters;

import com.google.common.base.Stopwatch;
import io.github.harvies.charon.util.JsonUtils;
import io.github.harvies.charon.util.TraceUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Activate
public class LogFilter implements Filter {

    private static final String TRACE_ID = "traceId";

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        Stopwatch stopwatch = Stopwatch.createStarted();
        if (StringUtils.isBlank(invocation.getAttachment(TRACE_ID))) {
            String traceId = TraceUtils.getTraceId();
            invocation.getObjectAttachments().put(TRACE_ID, traceId);
        }
        Result result = invoker.invoke(invocation);
        doLog(invoker, invocation, result, stopwatch);
        return result;
    }

    private void doLog(Invoker invoker, Invocation invocation, Result result, Stopwatch stopwatch) {
        RpcContext rpcContext = RpcContext.getContext();
        Map<String, Object> map = new HashMap<>();
        map.put("result", result.getValue());
        Map<String, Object> objectAttachments = invocation.getObjectAttachments();
        map.put(TRACE_ID, objectAttachments.get(TRACE_ID));
        map.put("url", invoker.getUrl());
        map.put("methodName", rpcContext.getMethodName());
        map.put("parameterTypes", rpcContext.getParameterTypes());
        map.put("arguments", rpcContext.getArguments());
        map.put("localAddress", rpcContext.getLocalAddress());
        map.put("remoteAddress", rpcContext.getRemoteAddress());
        map.put("remoteApplicationName", rpcContext.getRemoteApplicationName());
        map.put("cast", stopwatch.elapsed(TimeUnit.MILLISECONDS) + " ms");
        log.info(JsonUtils.toJSONString(map));
    }
}
