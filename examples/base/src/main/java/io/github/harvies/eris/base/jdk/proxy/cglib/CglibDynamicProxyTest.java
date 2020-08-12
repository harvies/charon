package io.github.harvies.eris.base.jdk.proxy.cglib;

import io.github.harvies.eris.base.annotations.RunSuccess;
import io.github.harvies.eris.base.jdk.proxy.Hello;
import io.github.harvies.eris.base.jdk.proxy.HelloImpl;

/**
 * CGLIB动态代理
 *
 * @author harvies
 */
@RunSuccess
public class CglibDynamicProxyTest {

    public static void main(String[] args) {
        HelloCglibProxy helloCglibProxy = new HelloCglibProxy();
        Hello instance = (Hello) helloCglibProxy.getInstance(new HelloImpl());
        instance.sayHello();
    }
}
