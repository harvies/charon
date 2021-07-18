package io.github.harvies.charon.util;

public class RequestTag {

    private static final String tag = "request_tag";
    /**
     * 请求标识
     */
    private static final ThreadLocal<String> REQUEST_TAG = new ThreadLocal<>();

    public static String getTagName() {
        return tag;
    }

    public static String get() {
        return REQUEST_TAG.get();
    }

    public static void set(String tag) {
        REQUEST_TAG.set(tag);
    }

    public static void remove() {
        REQUEST_TAG.remove();
    }
}
