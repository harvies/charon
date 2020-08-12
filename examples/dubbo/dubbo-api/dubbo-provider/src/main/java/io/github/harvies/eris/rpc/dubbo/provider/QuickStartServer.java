package io.github.harvies.eris.rpc.dubbo.provider;


import io.github.harvies.eris.dubbo.client.service.HelloService;
import io.github.harvies.eris.rpc.dubbo.provider.service.impl.HelloServiceImpl;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

import java.util.concurrent.CountDownLatch;

/**
 * Quick Start Server
 */
public class QuickStartServer {

    public static void main(String[] args) throws Exception {
        ServiceConfig<HelloService> service = new ServiceConfig<>();
        service.setApplication(new ApplicationConfig("first-dubbo-provider"));
        service.setRegistry(new RegistryConfig("zookeeper://192.168.7.77:2181"));
        service.setInterface(HelloService.class);
        service.setRef(new HelloServiceImpl());
        //设置集群容错模式(默认failover)
        service.setCluster("failover");
        //失败自动切换默认重试2次
        service.setRetries(2);
//        service.setSerialization();
        service.export();
        service.setTimeout(10000);
        System.out.println("dubbo service started");
        new CountDownLatch(1).await();
    }
}
