package io.github.harvies.charon.tests.spring.main;

import io.github.harvies.charon.tests.spring.inject.GetSetMethodInjectTest;
import io.github.harvies.charon.tests.spring.inject.instancefactory.InstanceFactoryInjectTest;
import io.github.harvies.charon.tests.spring.inject.staticfactory.StaticFactoryInjectTest;
import io.github.harvies.charon.tests.spring.service.LoginService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author harvies
 */
public class Main {
    public static void main(String[] args) {

        //ApplicationContext 继承自BeanFactory   XmlBeanFactory已被废弃
        //启动时会调用实现了SmartLifecycle的类的start()方法
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        //BeanFactory的getBean方法
        LoginService loginService = (LoginService) applicationContext.getBean("loginService2");
        boolean login = loginService.login("aaa", "aaa");
        System.err.println("login result:" + login);
        //获取bean数量
        int beanDefinitionCount = applicationContext.getBeanDefinitionCount();
        System.err.println("beanDefinitionCount:" + beanDefinitionCount);
        //loginService启动成功
        loginService.setRunning(true);

        //GetSet方法注入
        GetSetMethodInjectTest getSetMethodInjectTest = (GetSetMethodInjectTest) applicationContext.getBean("getSetMethodInjectTest");
        System.err.println("getSetMethodInjectTest:" + getSetMethodInjectTest);

        //静态工厂注入
        StaticFactoryInjectTest staticFactoryInjectTest = (StaticFactoryInjectTest) applicationContext.getBean("staticFactoryInjectTest");
        System.err.println("staticFactoryInjectTest:" + staticFactoryInjectTest);

        //实例工厂注入
        InstanceFactoryInjectTest instanceFactoryInjectTest = (InstanceFactoryInjectTest) applicationContext.getBean("instanceFactoryInjectTest");
        System.err.println("instanceFactoryInjectTest:" + instanceFactoryInjectTest);

        //关闭Spring上下文并销毁所有Bean
        applicationContext.close();
        //stop()时会调用实现了SmartLifecycle的类的stop()方法

//        applicationContext.stop();

        //ConfigurableApplicationContext 扩展于 ApplicationContext,它新增加了两个主要
        //的方法: refresh()和 close(),让 ApplicationContext 具有启动、刷新和关闭应用上下
        //文的能力。在应用上下文关闭的情况下调用 refresh()即可启动应用上下文,在已经启动
        //的状态下,调用 refresh()则清除缓存并重新装载配置信息,而调用 close()则可关闭应用
        //上下文。
//        applicationContext.refresh();
    }
}
