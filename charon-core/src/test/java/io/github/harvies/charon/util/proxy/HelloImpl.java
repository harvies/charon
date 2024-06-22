package io.github.harvies.charon.util.proxy;

/**
 * @author harvies
 */
public class HelloImpl implements Hello {
    @Override
    public void sayHello() {
        System.err.println("hello world");
    }
}
