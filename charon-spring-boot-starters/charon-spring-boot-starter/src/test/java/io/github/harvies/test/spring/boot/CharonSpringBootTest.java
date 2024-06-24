package io.github.harvies.test.spring.boot;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Value;
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
        Assert.assertEquals("GMT+9", property);

        //获取BeanB
        System.out.println(beanFactory.getBean(BeanA.class));
        System.out.println(beanFactory.getBean(BeanA.class));
        //获取FactoryBean本身
        System.out.println(beanFactory.getBean("&testFactoryBean"));
        System.out.println(beanFactory.getBean("&testFactoryBean"));

        //获取BeanB
        System.out.println(beanFactory.getBean("testFactoryBean"));
    }
}
