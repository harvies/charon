package io.github.harvies.charon.util;

import org.apache.commons.codec.binary.Base64;

public class Base64Utils {

    public static String encode(String str) {
        return Base64.encodeBase64String(str.getBytes());
    }

    public static String decode(String str) {
        byte[] bytes = Base64.decodeBase64(str);
        return org.apache.commons.codec.binary.StringUtils.newString(bytes, "UTF-8");
    }
}
