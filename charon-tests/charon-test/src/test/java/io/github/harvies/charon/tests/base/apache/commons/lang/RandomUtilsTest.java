package io.github.harvies.charon.tests.base.apache.commons.lang;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

public class RandomUtilsTest {
    @Test
    public void test() {
        for (int i = 0; i < 10; i++) {
            int result = RandomUtils.nextInt(1, 3);
            System.err.println(result);

        }
    }
}
