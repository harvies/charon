package io.github.harvies.charon.tests.base.jdk.clazz;

/**
 * 局部内部类(定义在方法中的类)
 * 定义在方法中的类,就是局部类。如果一个类只在某个方法中使用,则可以考虑使用局部类。
 *
 * @author harvies
 */
public class PartInnerClass {
    private static int a;
    private int b;

    public void test(final int c) {
        final int d = 1;
        class Inner {
            public void print() {
                System.out.println(c);
            }
        }
        new Inner().print();
    }

    public static void main(String[] args) {
        new PartInnerClass().test(2);
    }
}
