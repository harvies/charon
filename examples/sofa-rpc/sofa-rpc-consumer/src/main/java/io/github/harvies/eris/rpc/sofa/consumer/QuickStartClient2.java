package io.github.harvies.eris.rpc.sofa.consumer;

import com.alipay.sofa.rpc.common.RpcConstants;
import com.alipay.sofa.rpc.config.ConsumerConfig;
import com.alipay.sofa.rpc.config.RegistryConfig;
import io.github.harvies.eris.rpc.sofa.client.service.HelloService;

/**
 * Quick Start client
 */
public class QuickStartClient2 {
    public static void main(String[] args) {
        //设置默认日志实现类,见com.alipay.sofa.rpc.log.LoggerFactory.implClass
        System.setProperty("logger.impl", "com.alipay.sofa.rpc.log.SLF4JLoggerImpl");

        RegistryConfig registryConfig = new RegistryConfig()
                .setProtocol(RpcConstants.REGISTRY_PROTOCOL_ZK)
                .setAddress("frp.harvies.ml:22229");

        ConsumerConfig<HelloService> consumerConfig = new ConsumerConfig<HelloService>()
                .setInterfaceId(HelloService.class.getName()) // 指定接口
                .setRegistry(registryConfig);
        // 生成代理类
        HelloService helloService = consumerConfig.refer();
        while (true) {
            System.out.println(helloService.sayHello("world"));
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
            }
        }
    }
}
