package io.github.harvies.charon.tests.base.jdk.proxy.statiz;

import io.github.harvies.charon.tests.base.jdk.proxy.Hello;

/**
 * 代理模式中的所有角色（代理对象、目标对象、目标对象的接口）等都是在编译期就确定好的。
 * <p>
 * 静态代理的用途:
 * 1.控制真实对象的访问权限 通过代理对象控制对真实对象的使用权限。
 * 2.增强真实对象的功能 这个比较简单，通过代理可以在调用真实对象的方法的前后增加额外功能。
 *
 * @author harvies
 */
public class HelloProxy implements Hello {

    private Hello target;

    public HelloProxy(Hello target) {
        this.target = target;
    }

    @Override
    public void sayHello() {
        System.out.println("方法执行前-staticProxy");
        target.sayHello();
        System.out.println("方法执行后-staticProxy");
    }
}
