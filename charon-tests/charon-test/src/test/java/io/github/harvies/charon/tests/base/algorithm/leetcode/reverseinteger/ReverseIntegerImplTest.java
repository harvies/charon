package io.github.harvies.charon.tests.base.algorithm.leetcode.reverseinteger;

import io.github.harvies.charon.tests.base.algorithm.leetcode.twosum.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class ReverseIntegerImplTest extends BaseTest {

    @Test
    public void test1() {
        int reverse = new ReverseIntegerImpl().reverse(123456789);
        Assert.assertEquals(987654321, reverse);
    }
}
