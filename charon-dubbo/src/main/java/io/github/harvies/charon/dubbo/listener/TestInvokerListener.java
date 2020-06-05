package io.github.harvies.charon.dubbo.listener;

import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.common.logger.Logger;
import org.apache.dubbo.common.logger.LoggerFactory;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.listener.InvokerListenerAdapter;

import static org.apache.dubbo.rpc.Constants.DEPRECATED_KEY;

@Activate(DEPRECATED_KEY)
public class TestInvokerListener extends InvokerListenerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestInvokerListener.class);

    @Override
    public void referred(Invoker<?> invoker) throws RpcException {
        if (invoker.getUrl().getParameter(DEPRECATED_KEY, false)) {
            LOGGER.error("The service " + invoker.getInterface().getName() + " is DEPRECATED! Declare from " + invoker.getUrl());
        }
    }

}
