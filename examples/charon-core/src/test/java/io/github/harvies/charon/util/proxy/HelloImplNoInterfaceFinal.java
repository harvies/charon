package io.github.harvies.charon.util.proxy;

/**
 * @author harvies
 * <p>
 * CGLIB是针对类实现代理，主要是对指定的类生成一个子类，覆盖其中的方法，
 * <p>
 * 并覆盖其中方法实现增强，但是因为采用的是继承，所以该类或方法最好不要声明成final，
 * <p>
 * 对于final类或方法，是无法继承的。
 */
public final class HelloImplNoInterfaceFinal {
    public void sayHello() {
        System.err.println("hello world");
    }
}
