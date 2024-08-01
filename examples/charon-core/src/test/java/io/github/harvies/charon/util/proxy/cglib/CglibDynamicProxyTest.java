package io.github.harvies.charon.util.proxy.cglib;


import io.github.harvies.charon.util.proxy.Hello;
import io.github.harvies.charon.util.proxy.HelloImpl;

/**
 * CGLIB动态代理
 *
 * @author harvies
 */
public class CglibDynamicProxyTest {

    public static void main(String[] args) {
        HelloCglibProxy helloCglibProxy = new HelloCglibProxy();
        Hello instance = (Hello) helloCglibProxy.getInstance(new HelloImpl());
        instance.sayHello();
    }
}
