package io.github.harvies.eris.spring.namespace.demo.inject.instancefactory;

import io.github.harvies.eris.spring.namespace.demo.inject.FactoryDao;
import lombok.Data;

/**
 * @author harvies
 */
@Data
public class InstanceFactoryInjectTest {
    private FactoryDao factoryDao;

    //注入对象
    public void setFactoryDao(FactoryDao factoryDao) {
        this.factoryDao = factoryDao;
    }
}
