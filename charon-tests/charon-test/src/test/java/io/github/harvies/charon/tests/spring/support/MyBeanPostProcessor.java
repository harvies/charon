package io.github.harvies.charon.tests.spring.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 如果这个 Bean 关联了 BeanPostProcessor 接口,将会调用
 * postProcessBeforeInitialization(Object obj, String s)方法,BeanPostProcessor 经常被用
 * 作是 Bean 内容的更改,并且由于这个是在 Bean 初始化结束时调用那个的方法,也可以被应
 * 用于内存或缓存技术。
 * <p>
 * AbstractAutowireCapableBeanFactory  applyBeanPostProcessorsBeforeInitialization()调用
 *
 * @author harvies
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.err.println("bean初始化之前调用,beanName:" + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.err.println("bean初始化之后调用,beanName:" + beanName);
        return bean;
    }
}
