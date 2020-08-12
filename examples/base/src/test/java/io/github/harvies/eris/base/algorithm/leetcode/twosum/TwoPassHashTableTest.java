package io.github.harvies.eris.base.algorithm.leetcode.twosum;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

public class TwoPassHashTableTest extends BaseTest {

    @Test
    public void twoSum() {
        TwoSum twoSum = new TwoPassHashTable();
        argMap.forEach((integers, integer) -> {
            int[] sum = twoSum.twoSum(integers, integer);
            System.err.println(ToStringBuilder.reflectionToString(sum, ToStringStyle.SIMPLE_STYLE));
        });
    }
}
