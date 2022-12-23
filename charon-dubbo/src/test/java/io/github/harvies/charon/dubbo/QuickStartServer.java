package io.github.harvies.charon.dubbo;


import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * Quick Start Server
 */
public class QuickStartServer {

    public static void main(String[] args) throws Exception {
        ServiceConfig<HelloService> service = new ServiceConfig<>();
        service.setApplication(new ApplicationConfig("first-dubbo-provider"));
        RegistryConfig registryConfig = new RegistryConfig("multicast://224.5.6.7:1234");
        Map<String,String> parameters = new HashMap<>();
        parameters.put("unicast","false");
        registryConfig.setParameters(parameters);
        service.setRegistry(registryConfig);
        service.setInterface(HelloService.class);
        service.setRef(new HelloServiceImpl());
        //设置集群容错模式(默认failover)
        service.setCluster("failover");
        //失败自动切换默认重试2次
        service.setRetries(2);
        service.setFilter("log");
//        service.setSerialization();
        service.export();
        service.setTimeout(10000);
        System.out.println("dubbo service started");
        new CountDownLatch(1).await();
    }
}
