package io.github.harvies.eris.spring.namespace.demo.inject.instancefactory;

import io.github.harvies.eris.spring.namespace.demo.inject.FactoryDao;

public class DaoFactory { //实例工厂
    public FactoryDao getFactoryDaoImpl() {
        return new FactoryDaoImpl();
    }
}
