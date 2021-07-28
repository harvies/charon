package io.github.harvies.charon.gray;

import com.alibaba.ttl.TransmittableThreadLocal;

public class PriorityRouteGroup {

    private static final String tag = "priority-route-group";
    /**
     * 请求标识
     */
    private static final ThreadLocal<String> priorityRouteGroup = new TransmittableThreadLocal<>();

    public static String getTagName() {
        return tag;
    }

    public static String get() {
        return priorityRouteGroup.get();
    }

    public static void set(String tag) {
        priorityRouteGroup.set(tag);
    }

    public static void remove() {
        priorityRouteGroup.remove();
    }
}
