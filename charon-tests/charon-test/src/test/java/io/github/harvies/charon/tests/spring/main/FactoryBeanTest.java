package io.github.harvies.charon.tests.spring.main;

import io.github.harvies.charon.tests.spring.model.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author harvies
 */
public class FactoryBeanTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        for (int i = 0; i < 2; i++) {
            /**
             * 如果bean是一个BeanFactory则通过BeanFactory.getObject()创建Bean,
             * 见 AbstractBeanFactory.getObjectForBeanInstance()
             */
            User bean = (User) applicationContext.getBean("userFactory");
            System.err.println(bean);
        }
    }
}
