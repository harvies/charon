package io.github.harvies.eris.spring.namespace.demo.main;

import io.github.harvies.eris.spring.namespace.demo.service.UserService;
import io.github.harvies.eris.spring.namespace.demo.model.User;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * @author harvies
 */
public class DefaultListableBeanFactoryTest {
    public static void main(String[] args) {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClassName("io.github.harvies.eris.spring.namespace.demo.service.impl.UserServiceImpl");
        defaultListableBeanFactory.registerBeanDefinition("userService", beanDefinition);

        UserService userService = (UserService) defaultListableBeanFactory.getBean("userService");
        User user = userService.getById(1L);
        System.err.println(user);
    }
}
