package io.github.harvies.eris.base.jdk.proxy.jdk;

import io.github.harvies.eris.base.annotations.RunFailed;
import io.github.harvies.eris.base.jdk.proxy.HelloImplNoInterface;

/**
 * JDK动态代理
 * <p>
 * 只能对实现了接口的类生成代理，而不能针对类
 *
 * @author harvies
 */
@RunFailed
public class JdkDynamicProxyNoInterfaceTest {

    public static void main(String[] args) {
        HelloImplNoInterface hello = (HelloImplNoInterface) new HelloJdkDynamicProxy().bind(new HelloImplNoInterface());
        hello.sayHello();

        /**
         * Exception in thread "main" java.lang.ClassCastException: com.sun.proxy.$Proxy0 cannot be cast to io.github.harvies.eris.base.jdk.proxy.HelloImplNoInterface
         * 	at io.github.harvies.eris.base.jdk.proxy.jdk.JdkDynamicProxyNoInterfaceTest.main(JdkDynamicProxyNoInterfaceTest.java:13)
         */
    }
}
