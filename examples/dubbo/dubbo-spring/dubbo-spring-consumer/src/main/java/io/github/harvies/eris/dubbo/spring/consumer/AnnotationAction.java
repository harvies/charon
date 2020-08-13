package io.github.harvies.eris.dubbo.spring.consumer;

import io.github.harvies.eris.dubbo.client.service.HelloService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

@Component("annotationAction")
public class AnnotationAction {

    @DubboReference
    private HelloService annotationService;
    
    public String doSayHello(String name) {
        return annotationService.sayHello(name);
    }
}
