package io.github.harvies.eris.rpc.dubbo.provider.service.impl;


import io.github.harvies.eris.dubbo.client.service.HelloService;

/**
 * Quick Start demo implement
 *
 * @author harvies
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String string) {
        System.out.println("Server receive: " + string);
        return "hello " + string + " ！";
    }
}
