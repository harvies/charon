package io.github.harvies.eris.rpc.sofa.provider.service.impl;

import io.github.harvies.eris.rpc.sofa.client.service.HelloService;

/**
 * Quick Start demo implement
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String string) {
        System.out.println("Server receive: " + string);
        return "hello " + string + " ！";
    }
}
