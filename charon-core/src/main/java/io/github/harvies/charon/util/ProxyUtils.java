package io.github.harvies.charon.util;

import java.net.InetSocketAddress;
import java.net.Proxy;

public class ProxyUtils {

    private static final Proxy proxy = new Proxy(Proxy.Type.HTTP, InetSocketAddress.createUnresolved("192.168.7.2", 7890));

    public static Proxy getProxy() {
        return proxy;
    }
}
