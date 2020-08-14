package io.github.harvies.eris.base.algorithm.leetcode.twosum;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class OnePassHashTableTest extends BaseTest {

    @Test
    public void twoSum() {
        TwoSum twoSum = new OnePassHashTable();
        argMap.forEach((integers, integer) -> {
            int[] sum = twoSum.twoSum(integers, integer);
            assertThat(integers[sum[0]] + integers[sum[1]], is(integer));
        });
    }
}
