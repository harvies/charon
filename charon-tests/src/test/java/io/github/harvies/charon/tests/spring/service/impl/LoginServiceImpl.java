package io.github.harvies.charon.tests.spring.service.impl;

import io.github.harvies.charon.tests.spring.inject.FactoryDao;
import io.github.harvies.charon.tests.spring.service.LoginService;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.SmartLifecycle;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;

/**
 * LifeCycle:该接口是 Spring 2.0 加入的,该接口提供了 start()和 stop()两个方法,主要
 * 用于控制异步处理过程。在具体使用时,该接口同时被 ApplicationContext 实现及具体
 * Bean 实现, ApplicationContext 会将 start/stop 的信息传递给容器中所有实现了该接
 * 口的 Bean,以达到管理和控制 JMX、任务调度等目的。
 * <p>
 * 如果这个 Bean 已经实现了 BeanFactoryAware 接口,会调用它实现的 setBeanFactory,
 * setBeanFactory(BeanFactory)传递的是 Spring 工厂自身(可以用这个方式来获取其它 Bean,
 * 只需在 Spring 配置文件中配置一个普通的 Bean 就可以)。
 * <p>
 * 如果这个 Bean 已经实现了 ApplicationContextAware 接口,会调用
 * setApplicationContext(ApplicationContext)方法,传入 Spring 上下文(同样这个方式也
 * 可以实现步骤 4 的内容,但比 4 更好,因为 ApplicationContext 是 BeanFactory 的子接
 * 口,有更多的实现方法)
 *
 * @author harvies
 */

public class LoginServiceImpl implements LoginService, SmartLifecycle, BeanFactoryAware, ApplicationContextAware, DisposableBean, InitializingBean, BeanNameAware {
    private boolean running = false;
    private BeanFactory beanFactory;
    private ApplicationContext applicationContext;
    private String beanName;
    @Getter
    @Setter
    private String properties1;
    @Autowired
    private FactoryDao factoryDao;

    @Override
    public boolean login(String username, String password) {
        String[] beanDefinitionNames = ((DefaultListableBeanFactory) beanFactory).getBeanDefinitionNames();
        System.err.println("beanDefinitionNames:" + ToStringBuilder.reflectionToString(beanDefinitionNames, ToStringStyle.SIMPLE_STYLE));
        long startupDate = applicationContext.getStartupDate();
        System.err.println("Spring 容器启动时间" + Date.from(Instant.from(Instant.ofEpochMilli(startupDate))));
        System.err.println("beanName:" + beanName);
        System.err.println("properties1:" + properties1);
        System.err.println("factoryDao:" + factoryDao);

        return Objects.equals(username, password);
    }

    void init() {
        System.err.println("初始化时执行该方法");
    }

    void myDestroy() {
        System.err.println("销毁时时执行该方法");
    }

    @Override
    public void setRunning(boolean b) {
        running = b;
    }

    @Override
    public void start() {
        System.err.println("LoginService Spring 启动时执行 ");
    }

    @Override
    public void stop() {
        System.err.println("LoginService Spring 关闭时执行");
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void destroy() {
        System.err.println("LoginService is  been destroy");

    }

    @Override
    public void afterPropertiesSet() {
        System.err.println("afterPropertiesSet");
    }

    @Override
    public void setBeanName(String name) {
        beanName = name;
    }
}
