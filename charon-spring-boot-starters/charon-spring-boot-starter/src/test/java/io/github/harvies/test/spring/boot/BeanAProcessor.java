package io.github.harvies.test.spring.boot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.*;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BeanAProcessor implements ApplicationContextAware, EnvironmentAware, BeanPostProcessor, ApplicationContextInitializer<ConfigurableApplicationContext>, InitializingBean, DisposableBean {

    public static final String ORIGIN_BEAN_A = "originBeanA";
    public static final String MODIFY_BEAN_A = "modifyBeanA";

    public boolean processedProp;

    private Environment environment;

    private ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("2. 实现ApplicationContextAware接口，容器启动时会传入Spring上下文");
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof BeanA) {
            BeanA bean1 = (BeanA) bean;
            bean1.setName(ORIGIN_BEAN_A);
            log.info("5. 实现BeanPostProcessor接口，Bean实例化「前]被调用,可对Bean进行自定义处理 bean name properties:[{}] beanName:[{}]", ((BeanA) bean).getName(), beanName);
            bean1.setName(MODIFY_BEAN_A);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof BeanA) {
            log.info("6. 实现BeanPostProcessor接口，Bean实例化[后]被调用,可对Bean进行自定义处理 bean name properties:[{}] beanName:[{}]", ((BeanA) bean).getName(), beanName);
        }
        return bean;
    }

    @Override
    public void setEnvironment(Environment environment) {
        log.info("1. 实现 EnvironmentAware 接口，容器启动时会传入Environment环境信息");
        this.environment = environment;
    }

    @Override
    public void afterPropertiesSet() {
        log.info("3. 实现 InitializingBean 接口，Bean属性设置后，实例化前被调用");
        this.processedProp = true;
    }

    @Override
    public void destroy() {
        log.info("7. 实现 DisposableBean 接口 ，bean被销毁时被调用");
    }

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        log.info("0. BeanAProcessor ApplicationContextAware initialize");
    }
}
