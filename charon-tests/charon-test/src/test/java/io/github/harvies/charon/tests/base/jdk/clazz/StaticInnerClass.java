package io.github.harvies.charon.tests.base.jdk.clazz;

/**
 * 静态内部类
 * 定义在类内部的静态类,就是静态内部类。
 * <p>
 * 1.静态内部类可以访问外部类所有的静态变量和方法,即使是 private 的也一样。
 * 2.静态内部类和一般类一致,可以定义静态变量、方法,构造方法等。
 * 3.其它类使用静态内部类需要使用“外部类.静态内部类”方式,如下所示:Out.Inner inner =
 * new Out.Inner();inner.print();
 * 4.Java 集合类 HashMap 内部就有一个静态内部类 Entry。Entry 是 HashMap 存放元素的抽象,
 * HashMap 内部维护 Entry 数组用了存放元素,但是 Entry 对使用者是透明的。像这种和外部
 * 类关系密切的,且不依赖外部类实例的,都可以使用静态内部类。
 *
 * @author harvies
 */
public class StaticInnerClass {
    private static int a;
    private int b;

    public static class Inner {
        public void print() {
            System.out.println(a);
        }
    }

    public static void main(String[] args) {
        Inner inner =
                new Inner();
        inner.print();
    }
}
