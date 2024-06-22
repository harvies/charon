package io.github.harvies.charon.util.lang;

/**
 * @author harvies
 */

import java.text.NumberFormat;

/**
 * int能表示2^32个元素
 * 从-2^31~2^31-1(-1是因为有0)  -2147483648 ~ 2147483647(21亿)
 * @author harvies
 */
public class IntegerTest {
    public static void main(String[] args) {
        System.err.println("个数:" + NumberFormat.getInstance().format(Math.pow(2, 32)));
        System.err.println("位数:" + Integer.SIZE);
        System.err.println("字节数:" + Integer.BYTES);
        System.err.println("最小值:" + Integer.MIN_VALUE);
        System.err.println("最大值:" + Integer.MAX_VALUE);
        System.err.println(-Math.pow(2, 31) == Integer.MIN_VALUE);
        System.err.println(Math.pow(2, 31) - 1 == Integer.MAX_VALUE);
    }
}
