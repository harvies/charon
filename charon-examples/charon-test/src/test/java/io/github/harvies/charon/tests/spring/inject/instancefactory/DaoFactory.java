package io.github.harvies.charon.tests.spring.inject.instancefactory;


import io.github.harvies.charon.tests.spring.inject.FactoryDao;

public class DaoFactory { //实例工厂
    public FactoryDao getFactoryDaoImpl() {
        return new FactoryDaoImpl();
    }
}
