package io.github.harvies.charon.util;

import org.junit.Test;

/**
 * Java 语言的方法调用只支持参数的值传递
 */
public class ValueTransfer {

    @Test
    public void test() {
        Integer a = 1;
        test1(a);
        System.err.println("test a:" + a);
    }

    void test1(Integer a) {
        a = 5;
        System.err.println("test1 a:" + a);
    }
}
