package io.github.harvies.charon.util.proxy.statiz;


import io.github.harvies.charon.util.proxy.Hello;
import io.github.harvies.charon.util.proxy.HelloImpl;

/**
 * 静态代理(代理模式)
 *
 * @author harvies
 */
public class StaticProxyTest {

    public static void main(String[] args) {
        HelloImpl target = new HelloImpl();
        Hello proxy = new HelloProxy(target);
        proxy.sayHello();
    }
}
