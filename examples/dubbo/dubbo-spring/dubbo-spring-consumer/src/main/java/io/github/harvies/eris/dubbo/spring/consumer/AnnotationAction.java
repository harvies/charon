package io.github.harvies.eris.dubbo.spring.consumer;

import io.github.harvies.eris.dubbo.client.service.HelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

@Component("annotationAction")
public class AnnotationAction {

    @Reference
    private HelloService annotationService;
    
    public String doSayHello(String name) {
        return annotationService.sayHello(name);
    }
}
