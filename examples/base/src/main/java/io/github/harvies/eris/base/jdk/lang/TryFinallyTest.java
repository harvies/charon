package io.github.harvies.eris.base.jdk.lang;

/**
 * Try Finally测试
 *
 * @author harvies
 */
public class TryFinallyTest {
    public static void main(String[] args) {
        System.err.println(test1());
        System.err.println("--------");
        System.err.println(test2());
    }

    private static Integer test1() {
        Integer i;
        try {
            i = 1;
            return i;

        } finally {
            /**
             * 不会影响到try里的return结果(先走try里的return再走finally)
             */
            i = 2;
            System.err.println(i);
        }
    }

    private static Integer test2() {
        Integer i;
        try {
            i = 1;
            return i;

        } finally {
            i = 2;
            System.err.println(i);
            /**
             * 会覆盖try里的retuen结果
             */
            return i;
        }
    }
}
