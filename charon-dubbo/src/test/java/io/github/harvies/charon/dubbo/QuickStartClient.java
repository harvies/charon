package io.github.harvies.charon.dubbo;


import io.github.harvies.charon.util.PropertiesUtils;
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
        String zkUrl = PropertiesUtils.getDefaultProperty("charon.zk.url");
        ReferenceConfig<HelloService> reference = new ReferenceConfig<>();
        reference.setApplication(new ApplicationConfig("first-dubbo-consumer"));
        RegistryConfig registryConfig = new RegistryConfig("zookeeper://" + zkUrl);
        registryConfig.setTimeout(10000);
        reference.setRegistry(registryConfig);
        reference.setInterface(HelloService.class);
        //设置客户端超时,默认1s
        reference.setTimeout(CommonConstants.DEFAULT_TIMEOUT);
        HelloService service = reference.get();
        String message = service.sayHello("dubbo");
        System.out.println(message);
    }
}
