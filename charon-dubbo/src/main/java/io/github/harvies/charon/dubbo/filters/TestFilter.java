package io.github.harvies.charon.dubbo.filters;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;

@Slf4j
@Activate
public class TestFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        if (StringUtils.isBlank(invocation.getAttachment("traceId"))) {
            invocation.setAttachment("traceId", TraceContext.traceId());
        }
        log.info("invoker begin:[{}] invocation:[{}] traceId:[{}]", invoker, invocation, invocation.getAttachment("traceId"));
        Result invoke = invoker.invoke(invocation);
        log.info("invoker end:[{}] invocation:[{}] traceId:[{}]", invoker, invocation, invocation.getAttachment("traceId"));
        return invoke;
    }
}
