package io.github.harvies.eris.sofa.provider.service.impl;

import io.github.harvies.eris.sofa.client.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class TestService {
    @Autowired
    private HelloService helloService;

    @PostConstruct
    public void init() {
        String sayHello = helloService.sayHello("provider sayHello");
        System.err.println(sayHello);
    }
}
