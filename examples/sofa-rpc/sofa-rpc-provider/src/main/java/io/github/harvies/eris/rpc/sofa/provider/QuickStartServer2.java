package io.github.harvies.eris.rpc.sofa.provider;

import com.alipay.sofa.rpc.common.RpcConstants;
import com.alipay.sofa.rpc.config.ProviderConfig;
import com.alipay.sofa.rpc.config.RegistryConfig;
import com.alipay.sofa.rpc.config.ServerConfig;
import io.github.harvies.eris.rpc.sofa.client.service.HelloService;
import io.github.harvies.eris.rpc.sofa.provider.service.impl.HelloServiceImpl;

/**
 * Quick Start Server
 */
public class QuickStartServer2 {


    public static void main(String[] args) {

        ServerConfig serverConfig = new ServerConfig()
                .setProtocol("bolt") // 设置一个协议，默认bolt
//                .setPort(12200) // 设置一个端口，默认12200
                .setDaemon(false); // 非守护线程

        RegistryConfig registryConfig = new RegistryConfig()
                .setProtocol(RpcConstants.REGISTRY_PROTOCOL_ZK)
                .setAddress("frp.harvies.ml:22229");

        ProviderConfig<HelloService> providerConfig = new ProviderConfig<HelloService>()
                .setInterfaceId(HelloService.class.getName()) // 指定接口
                .setRef(new HelloServiceImpl()) // 指定实现
                .setServer(serverConfig)// 指定服务端
                .setRegistry(registryConfig)
                .setRegister(true);

        providerConfig.export(); // 发布服务

    }
}
