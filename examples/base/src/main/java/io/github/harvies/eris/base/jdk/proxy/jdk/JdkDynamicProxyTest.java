package io.github.harvies.eris.base.jdk.proxy.jdk;

import io.github.harvies.charon.annotations.RunSuccess;
import io.github.harvies.eris.base.jdk.proxy.Hello;
import io.github.harvies.eris.base.jdk.proxy.HelloImpl;

/**
 * JDK动态代理
 *
 * @author harvies
 */
@RunSuccess
public class JdkDynamicProxyTest {

    public static void main(String[] args) {
        Hello hello = (Hello) new HelloJdkDynamicProxy().bind(new HelloImpl());
        hello.sayHello();
    }
}
