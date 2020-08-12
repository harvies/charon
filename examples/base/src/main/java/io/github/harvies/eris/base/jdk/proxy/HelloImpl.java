package io.github.harvies.eris.base.jdk.proxy;

/**
 * @author harvies
 */
public class HelloImpl implements Hello {
    @Override
    public void sayHello() {
        System.err.println("hello world");
    }
}
