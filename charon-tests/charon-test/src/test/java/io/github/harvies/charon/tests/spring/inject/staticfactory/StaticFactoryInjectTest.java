package io.github.harvies.charon.tests.spring.inject.staticfactory;

import io.github.harvies.charon.tests.spring.inject.FactoryDao;
import lombok.Data;

/**
 * 静态工厂注入
 * <p>
 * 静态工厂顾名思义,就是通过调用静态工厂的方法来获取自己需要的对象,为了让 spring 管理所
 * 有对象,我们不能直接通过"工程类.静态方法()"来获取对象,而是依然通过 spring 注入的形式获
 * 取:
 *
 * @author harvies
 */
@Data
public class StaticFactoryInjectTest {
    private FactoryDao staticFactoryDao; //注入对象

    //注入对象的 set 方法
    public void setStaticFactoryDao(FactoryDao staticFactoryDao) {
        this.staticFactoryDao = staticFactoryDao;
    }
}
