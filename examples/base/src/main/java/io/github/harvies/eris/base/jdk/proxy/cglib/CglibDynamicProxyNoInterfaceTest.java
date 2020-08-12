package io.github.harvies.eris.base.jdk.proxy.cglib;

import io.github.harvies.eris.base.annotations.RunSuccess;
import io.github.harvies.eris.base.jdk.proxy.HelloImplNoInterface;

/**
 * CGLIB动态代理
 *
 * @author harvies
 */
@RunSuccess
public class CglibDynamicProxyNoInterfaceTest {

    public static void main(String[] args) {
        HelloCglibProxy helloCglibProxy = new HelloCglibProxy();
        HelloImplNoInterface instance = (HelloImplNoInterface) helloCglibProxy.getInstance(new HelloImplNoInterface());
        instance.sayHello();
    }
}
