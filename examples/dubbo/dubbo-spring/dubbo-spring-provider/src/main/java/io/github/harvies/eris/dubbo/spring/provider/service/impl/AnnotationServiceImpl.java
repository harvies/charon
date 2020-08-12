package io.github.harvies.eris.dubbo.spring.provider.service.impl;

import io.github.harvies.eris.dubbo.client.service.HelloService;
import org.springframework.stereotype.Service;

@Service
public class AnnotationServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        System.out.println("name:" + name);
        return "annotation: hello, " + name;
    }
}