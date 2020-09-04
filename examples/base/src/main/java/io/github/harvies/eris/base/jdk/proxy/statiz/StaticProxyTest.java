package io.github.harvies.eris.base.jdk.proxy.statiz;

import io.github.harvies.charon.annotations.RunSuccess;
import io.github.harvies.eris.base.jdk.proxy.Hello;
import io.github.harvies.eris.base.jdk.proxy.HelloImpl;

/**
 * 静态代理(代理模式)
 *
 * @author harvies
 */
@RunSuccess
public class StaticProxyTest {

    public static void main(String[] args) {
        HelloImpl target = new HelloImpl();
        Hello proxy = new HelloProxy(target);
        proxy.sayHello();
    }
}
