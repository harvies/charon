package io.github.harvies.charon.spring.boot.factorybean;

import io.github.harvies.charon.spring.boot.BaseTest;
import io.github.harvies.charon.spring.boot.SpringContextHolder;
import org.junit.Test;

public class CharonSpringBootTestFactoryBean extends BaseTest {

    @Test
    public void test() {
        //获取BeanB
        System.out.println(SpringContextHolder.getBean(BeanB.class).toString());
        System.out.println(SpringContextHolder.getBean(BeanB.class).toString());
        //获取FactoryBean本身
        System.out.println(SpringContextHolder.getBean("&testFactoryBean").toString());
        System.out.println(SpringContextHolder.getBean("&testFactoryBean").toString());

        //获取BeanB
        System.out.println(SpringContextHolder.getBean("testFactoryBean").toString());

    }
}
