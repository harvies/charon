package io.github.harvies.charon.tests.base.datastructure.bitset;

import org.apache.commons.lang3.RandomUtils;

/**
 * 使用boolean数组实现位图
 *
 * @author harvies
 */
public class BooleanArrayTest {
    public static void main(String[] args) {
        boolean[] booleans = new boolean[100000000];
        for (int i = 0; i < 10; i++) {
            int nextInt = RandomUtils.nextInt(0, 100000000);
            System.err.println(nextInt);
            booleans[nextInt] = true;
        }

        for (int i = 0; i < booleans.length; i++) {
            if (booleans[i]) {
                System.err.println("存在:" + i);
            }
        }
    }
}
