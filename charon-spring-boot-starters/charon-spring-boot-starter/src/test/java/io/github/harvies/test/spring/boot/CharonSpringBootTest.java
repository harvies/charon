package io.github.harvies.test.spring.boot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.core.env.Environment;

import jakarta.annotation.Resource;

public class CharonSpringBootTest extends BaseTest {
    @Value("${spring.jackson.time-zone:11}")
    private String property;

    @Resource
    private Environment environment;

    @Resource
    private BeanFactory beanFactory;

    @Test
    public void test() {
        String property = environment.getProperty("spring.jackson.time-zone");
        Assertions.assertEquals("GMT+9", property);

        //获取BeanA
        BeanA beanA = beanFactory.getBean(BeanA.class);
        System.out.println(beanA);
        beanA.methodA();
        System.out.println(beanFactory.getBean(BeanA.class));
//        //获取FactoryBean本身
        System.out.println(beanFactory.getBean("&beanAFactoryBean"));
        System.out.println(beanFactory.getBean("&beanAFactoryBean"));

        //获取BeanA
        System.out.println(beanFactory.getBean("beanAFactoryBean"));
    }

    @Test
    public void testBeanDefinition() {
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) beanFactory;
        BeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClassName("io.github.harvies.test.spring.boot.BeanA");
        defaultListableBeanFactory.registerBeanDefinition("beanA", beanDefinition);
        BeanA beanA = (BeanA) defaultListableBeanFactory.getBean("beanA");
        boolean methodA = beanA.methodA();
        System.err.println(methodA);
    }
}
