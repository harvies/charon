package io.github.harvies.charon.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Md5Utils
 */
public class Md5Utils {

    public static String encode(String str) {
        return DigestUtils.md5Hex(str);
    }
}
