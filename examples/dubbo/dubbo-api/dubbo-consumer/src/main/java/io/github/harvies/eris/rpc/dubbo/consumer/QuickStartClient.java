package io.github.harvies.eris.rpc.dubbo.consumer;


import io.github.harvies.eris.dubbo.client.service.HelloService;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;

/**
 * Quick Start client
 *
 * @author harvies
 */
public class QuickStartClient {
    public static void main(String[] args) {
        ReferenceConfig<HelloService> reference = new ReferenceConfig<>();
        reference.setApplication(new ApplicationConfig("first-dubbo-consumer"));
        reference.setRegistry(new RegistryConfig("zookeeper://192.168.7.77:2181"));
        reference.setInterface(HelloService.class);
        //设置客户端超时,默认1s
        reference.setTimeout(CommonConstants.DEFAULT_TIMEOUT);
        HelloService service = reference.get();
        String message = service.sayHello("dubbo");
        System.out.println(message);
    }
}
