package io.github.harvies.charon.dubbo.filters;

import com.google.common.base.Stopwatch;
import io.github.harvies.charon.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Activate
public class LogFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        Stopwatch stopwatch = Stopwatch.createStarted();
        if (StringUtils.isBlank(invocation.getAttachment("traceId"))) {
            invocation.setAttachment("traceId", TraceContext.traceId());
        }
        Result result = invoker.invoke(invocation);
        doLog(result, stopwatch);
        return result;
    }

    private void doLog(Result result, Stopwatch stopwatch) {
        RpcContext rpcContext = RpcContext.getContext();
        Map<String, Object> map = new HashMap<>();
        map.put("result", result.getValue());
        map.put("attachments", rpcContext.getObjectAttachments());
        map.put("url", rpcContext.getUrl());
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
