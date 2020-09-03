package io.github.harvies.charon.util;

import java.util.UUID;

public class RandomUtils extends org.apache.commons.lang3.RandomUtils {
    /**
     * 获取UUID
     *
     * @return
     */
    public static String uuid() {
        return UUID.randomUUID().toString();
    }
}
