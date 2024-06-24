package io.github.harvies.test.spring.boot;

import org.springframework.beans.factory.FactoryBean;

public class BeanAFactoryBean implements FactoryBean<BeanA> {
    @Override
    public BeanA getObject() {
        return new BeanA();
    }

    @Override
    public Class<BeanA> getObjectType() {
        return BeanA.class;
    }

    //控制BeanB是单例还是原型
    @Override
    public boolean isSingleton() {
        return false;
    }
}
