package io.github.harvies.charon.dubbo.filters;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;

@Slf4j
@Activate
public class LogFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        Stopwatch stopwatch = Stopwatch.createStarted();
        if (StringUtils.isBlank(invocation.getAttachment("traceId"))) {
            invocation.setAttachment("traceId", TraceContext.traceId());
        }
        Result invoke = invoker.invoke(invocation);
        log.info("invoker end:[{}] invocation:[{}] traceId:[{}] cast:[{}] ms", invoker, invocation, invocation.getAttachment("traceId"), stopwatch.elapsed().toMillis());
        return invoke;
    }
}
