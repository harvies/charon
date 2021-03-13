package io.github.harvies.charon.tests.base.datastructure.bitset;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;

import java.util.BitSet;

/**
 * BitSet底层使用long[]
 *
 * @author harvies
 */
@Slf4j
public class BitSetTest {
    public static void main(String[] args) {
        /**
         * 1亿个数
         */
        BitSet bitSet = new BitSet(100000000);
        /**
         * 生成10个数,0-1亿之间的数
         */
        for (int i = 0; i < 10; i++) {
            int nextInt = RandomUtils.nextInt(0, 100000000);
            System.err.println("nextInt:" + nextInt);
            bitSet.set(nextInt);
        }

        for (int i = 0; i < 10000000; i++) {
            int nextInt = RandomUtils.nextInt(0, 100000000);
            boolean b = bitSet.get(nextInt);
            if (b) {
                System.err.println("存在:" + nextInt);
            }
        }

    }
}
