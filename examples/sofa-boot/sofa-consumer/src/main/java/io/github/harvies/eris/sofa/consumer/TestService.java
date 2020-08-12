package io.github.harvies.eris.sofa.consumer;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.alipay.sofa.runtime.api.annotation.SofaReferenceBinding;
import io.github.harvies.eris.sofa.client.service.HelloService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class TestService {

    @SofaReference(interfaceType = HelloService.class, binding = @SofaReferenceBinding(bindingType = "bolt"))
    private HelloService helloService;

    @PostConstruct
    public void init() {
        String sayHello = helloService.sayHello("sayHello");
        System.err.println(sayHello);
    }
}
