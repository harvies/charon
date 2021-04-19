package io.github.harvies.charon.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RandomUtilsTest {

    @Test
    void uuid() {
        System.err.println(RandomUtils.uuid());
    }

    @Test
    void nextInt() {
        int begin = 1;
        int end = 10;
        for (int i = 0; i < 100; i++) {
            int random = RandomUtils.nextInt(begin, end);
            assertTrue(random >= begin && random < end);
        }
    }
}
