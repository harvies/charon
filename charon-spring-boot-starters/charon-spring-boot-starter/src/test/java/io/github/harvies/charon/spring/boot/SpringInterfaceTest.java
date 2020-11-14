package io.github.harvies.charon.spring.boot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SpringInterfaceTest implements ApplicationContextAware, EnvironmentAware, BeanPostProcessor, InitializingBean, DisposableBean {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("实现ApplicationContextAware接口，容器启动时会传入Spring上下文");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof BeanA) {
            BeanA bean1 = (BeanA) bean;
            bean1.setName("testName");
            log.info("实现BeanPostProcessor接口， Bean实例化前被调用,可对Bean进行自定义处理 beanName:[{}]", beanName);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof BeanA) {
            log.info("实现BeanPostProcessor接口，Bean实例化后被调用,可对Bean进行自定义处理 bean name properties:[{}] beanName:[{}]", ((BeanA) bean).getName(), beanName);
        }
        return bean;
    }

    @Override
    public void setEnvironment(Environment environment) {
        log.info("实现 EnvironmentAware 接口，容器启动时会传入Environment环境信息");
    }

    @Override
    public void afterPropertiesSet() {
        log.info("实现 InitializingBean 接口，Bean属性设置后，实例化前被调用");
    }

    @Override
    public void destroy() {
        log.info("实现 DisposableBean 接口 ，bean被销毁时被调用");
    }
}
