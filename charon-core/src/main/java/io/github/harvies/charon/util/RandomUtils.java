package io.github.harvies.charon.util;

import java.util.UUID;

public class RandomUtils extends org.apache.commons.lang3.RandomUtils {
    /**
     * 获取UUID
     *
     */
    public static String uuid() {
        return UUID.randomUUID().toString();
    }

    /**
     *
     * @param startInclusive 包括
     * @param endExclusive 不包括
     */
    public static int nextInt(final long startInclusive, final long endExclusive) {
        return org.apache.commons.lang3.RandomUtils.nextInt();
    }
}
