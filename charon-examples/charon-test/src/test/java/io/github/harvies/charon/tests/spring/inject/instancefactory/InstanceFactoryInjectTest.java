package io.github.harvies.charon.tests.spring.inject.instancefactory;

import io.github.harvies.charon.tests.spring.inject.FactoryDao;
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
