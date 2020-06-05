package io.github.harvies.charon.dubbo.filters;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

@Slf4j
@Activate
public class TestFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        log.info("invoker begin:[{}] invocation:[{}]", invoker, invocation);
        Result invoke = invoker.invoke(invocation);
        log.info("invoker end:[{}] invocation:[{}]", invoker, invocation);
        return invoke;
    }
}
