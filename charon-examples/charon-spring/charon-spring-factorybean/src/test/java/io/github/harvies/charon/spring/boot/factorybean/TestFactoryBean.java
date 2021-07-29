package io.github.harvies.charon.spring.boot.factorybean;

import org.springframework.beans.factory.FactoryBean;

public class TestFactoryBean implements FactoryBean<BeanB> {
    @Override
    public BeanB getObject() {
        return new BeanB();
    }

    @Override
    public Class<BeanB> getObjectType() {
        return BeanB.class;
    }

    //控制BeanB是单例还是原型
    @Override
    public boolean isSingleton() {
        return false;
    }
}
