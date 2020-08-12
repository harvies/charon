package io.github.harvies.eris.dubbo.spring.provider;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.locks.LockSupport;

public class DubboSpringProviderBoot {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProviderConfiguration.class);
        context.start();
        LockSupport.park();
    }
}
