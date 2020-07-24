package io.github.harvies.charon.spring.boot.fastjson;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import java.lang.reflect.Method;

@Slf4j
public class FastJsonApplicationInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        log.info("开始执行charon-fastjson-spring-boot-starter context初始化方法");
        try {
            BeanFactory factory = configurableApplicationContext.getBeanFactory();
            if (factory instanceof BeanDefinitionRegistry) {
                Class<?> scannerClass = Class.forName("org.springframework.context.annotation.ClassPathBeanDefinitionScanner");
                Object scanner = scannerClass.getConstructor(new Class<?>[]{BeanDefinitionRegistry.class, boolean.class}).newInstance((BeanDefinitionRegistry) factory, true);
                // scan packages
                Method scan = scannerClass.getDeclaredMethod("scan", String[].class);
                String[] basePackages = new String[1];
                basePackages[0] = "aaa.bbb";
                scan.invoke(scanner, new Object[]{basePackages});
                log.info("扫描 [aaa.bbb] 完毕");
            }
        } catch (Exception ex) {
            log.error("执行charon-fastjson-spring-boot-starter context初始化方法异常", ex);
        }
    }
}
