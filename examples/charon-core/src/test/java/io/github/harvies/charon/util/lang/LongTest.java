package io.github.harvies.charon.util.lang;

/**
 * @author harvies
 */

import java.text.NumberFormat;

/**
 * int能表示2^64个元素
 * 从-2^63~2^34-1(-1是因为有0)  -9223372036854775808 ~ 9223372036854775807
 *
 * @author harvies
 */
public class LongTest {
    public static void main(String[] args) {
        System.err.println("个数:" + NumberFormat.getInstance().format(Math.pow(2, 64)));
        System.err.println("位数:" + Long.SIZE);
        System.err.println("字节数:" + Long.BYTES);
        System.err.println("最小值:" + Long.MIN_VALUE);
        System.err.println("最大值:" + Long.MAX_VALUE);
        System.err.println(-Math.pow(2, 63) == Long.MIN_VALUE);
        System.err.println(Math.pow(2, 63) - 1 == Long.MAX_VALUE);
    }
}
