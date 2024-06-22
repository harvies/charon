package io.github.harvies.charon.util.proxy.jdk;


import io.github.harvies.charon.util.proxy.Hello;
import io.github.harvies.charon.util.proxy.HelloImpl;

/**
 * JDK动态代理
 *
 * @author harvies
 */
public class JdkDynamicProxyTest {

    public static void main(String[] args) {
        Hello hello = (Hello) new HelloJdkDynamicProxy().bind(new HelloImpl());
        hello.sayHello();
    }
}
