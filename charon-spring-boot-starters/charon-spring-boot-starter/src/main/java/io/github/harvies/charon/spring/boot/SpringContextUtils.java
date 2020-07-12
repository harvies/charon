package io.github.harvies.charon.spring.boot;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ConfigurableApplicationContext;

public class SpringContextUtils {
    private static ConfigurableApplicationContext applicationContext;

    public static void setApplicationContext(ConfigurableApplicationContext ac) {
        applicationContext = ac;
    }

    public static BeanFactory getApplicationContext() {
        return applicationContext;
    }
}
