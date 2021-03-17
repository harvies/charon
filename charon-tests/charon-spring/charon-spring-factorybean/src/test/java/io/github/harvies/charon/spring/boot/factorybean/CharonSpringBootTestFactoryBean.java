package io.github.harvies.charon.spring.boot.factorybean;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;

import javax.annotation.Resource;

public class CharonSpringBootTestFactoryBean extends BaseTest {

    @Resource
    private BeanFactory beanFactory;

    @Test
    public void test() {
        //获取BeanB
        System.out.println(beanFactory.getBean(BeanB.class).toString());
        System.out.println(beanFactory.getBean(BeanB.class).toString());
        //获取FactoryBean本身
        System.out.println(beanFactory.getBean("&testFactoryBean").toString());
        System.out.println(beanFactory.getBean("&testFactoryBean").toString());

        //获取BeanB
        System.out.println(beanFactory.getBean("testFactoryBean").toString());

    }
}
