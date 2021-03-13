package io.github.harvies.charon.tests.base.jdk.lang;

/**
 * @author harvies
 */
public class IntegerTest2 {
    public static void main(String[] args) {
        Integer a = Integer.valueOf(1);
        System.out.println("identityHashCode=" + System.identityHashCode(a) + ",value:" + a);
        change(a);
        //输出结果仍然是1
        System.out.println("identityHashCode=" + System.identityHashCode(a) + ",value:" + a);
    }

    private static void change(Integer a) {
        System.out.println("identityHashCode=" + System.identityHashCode(a) + ",value:" + a);
        a = Integer.valueOf(5);
        System.out.println("identityHashCode=" + System.identityHashCode(a) + ",value:" + a);
    }
}
