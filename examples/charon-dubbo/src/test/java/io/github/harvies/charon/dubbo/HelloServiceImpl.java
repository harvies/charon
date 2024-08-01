package io.github.harvies.charon.dubbo;


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
