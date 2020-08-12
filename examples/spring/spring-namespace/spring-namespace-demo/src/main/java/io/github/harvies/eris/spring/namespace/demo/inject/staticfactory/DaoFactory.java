package io.github.harvies.eris.spring.namespace.demo.inject.staticfactory;

import io.github.harvies.eris.spring.namespace.demo.inject.FactoryDao;

/**
 * @author harvies
 */
public class DaoFactory { //静态工厂
    public static final FactoryDao getStaticFactoryDaoImpl() {
        return new StaticFactoryDaoImpl();
    }
}
