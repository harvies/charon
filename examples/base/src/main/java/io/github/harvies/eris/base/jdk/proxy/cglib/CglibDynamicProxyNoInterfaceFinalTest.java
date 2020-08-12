package io.github.harvies.eris.base.jdk.proxy.cglib;

import io.github.harvies.eris.base.annotations.RunFailed;
import io.github.harvies.eris.base.jdk.proxy.HelloImplNoInterfaceFinal;

/**
 * CGLIB动态代理
 *
 * @author harvies
 */
@RunFailed
public class CglibDynamicProxyNoInterfaceFinalTest {

    public static void main(String[] args) {
        HelloCglibProxy helloCglibProxy = new HelloCglibProxy();
        HelloImplNoInterfaceFinal instance = (HelloImplNoInterfaceFinal) helloCglibProxy.getInstance(new HelloImplNoInterfaceFinal());
        /**
         * Exception in thread "main" java.lang.IllegalArgumentException: Cannot subclass final class class io.github.harvies.eris.base.jdk.proxy.HelloImplNoInterfaceFinal
         * 	at net.sf.cglib.proxy.Enhancer.generateClass(Enhancer.java:446)
         * 	at net.sf.cglib.core.DefaultGeneratorStrategy.generate(DefaultGeneratorStrategy.java:25)
         * 	at net.sf.cglib.core.AbstractClassGenerator.create(AbstractClassGenerator.java:216)
         * 	at net.sf.cglib.proxy.Enhancer.createHelper(Enhancer.java:377)
         * 	at net.sf.cglib.proxy.Enhancer.create(Enhancer.java:285)
         * 	at io.github.harvies.eris.base.jdk.proxy.cglib.HelloCglibProxy.getInstance(HelloCglibProxy.java:23)
         * 	at io.github.harvies.eris.base.jdk.proxy.cglib.CglibDynamicProxyNoInterfaceFinalTest.main(CglibDynamicProxyNoInterfaceFinalTest.java:14)
         */
        instance.sayHello();
    }
}
