package io.github.harvies.test.spring.boot.factorybean;

import io.github.harvies.test.spring.boot.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;

import jakarta.annotation.Resource;

public class CharonSpringBootTestFactoryBean extends BaseTest {

    @Resource
    private BeanFactory beanFactory;

    @Test
    public void test() {
        //获取BeanB
        System.out.println(beanFactory.getBean(BeanB.class));
        System.out.println(beanFactory.getBean(BeanB.class));
        //获取FactoryBean本身
        System.out.println(beanFactory.getBean("&testFactoryBean"));
        System.out.println(beanFactory.getBean("&testFactoryBean"));

        //获取BeanB
        System.out.println(beanFactory.getBean("testFactoryBean"));

    }
}
