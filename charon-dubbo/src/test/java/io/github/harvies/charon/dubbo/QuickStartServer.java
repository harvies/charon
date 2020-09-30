package io.github.harvies.charon.dubbo;


import io.github.harvies.charon.util.PropertiesUtils;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

import java.util.concurrent.CountDownLatch;

/**
 * Quick Start Server
 */
public class QuickStartServer {

    public static void main(String[] args) throws Exception {
        String zkUrl = PropertiesUtils.getDefaultProperty("charon.zk.url");
        ServiceConfig<HelloService> service = new ServiceConfig<>();
        service.setApplication(new ApplicationConfig("first-dubbo-provider"));
        service.setRegistry(new RegistryConfig("zookeeper://" + zkUrl));
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
