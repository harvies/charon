package io.github.harvies.eris.sofa.provider.service.impl;

import com.alipay.sofa.runtime.api.annotation.SofaService;
import com.alipay.sofa.runtime.api.annotation.SofaServiceBinding;
import io.github.harvies.eris.sofa.client.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * Quick Start demo implement
 */
@Service
@SofaService(interfaceType = HelloService.class, bindings = {@SofaServiceBinding(bindingType = "bolt")})
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String string) {
        System.out.println("Server receive: " + string);
        return "hello " + string + " ！";
    }
}
