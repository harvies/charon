package io.github.harvies.eris.dubbo.spring.consumer;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DubboSpringConsumerBoot {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConsumerConfiguration.class);
        context.start();
        final AnnotationAction annotationAction = (AnnotationAction) context.getBean("annotationAction");
        String hello = annotationAction.doSayHello("world");
        System.out.printf("hello:" + hello);
    }
}
