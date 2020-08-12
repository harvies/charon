package io.github.harvies.eris.spring.namespace.demo.factory;

import io.github.harvies.eris.spring.namespace.demo.model.User;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * 获取User的工厂
 *
 * @author harvies
 */
@Component
public class UserFactory implements FactoryBean<User> {
    @Override
    public User getObject() {
        long currentTimeMillis = System.currentTimeMillis();
        return User.builder().id(currentTimeMillis)
                .username("username" + currentTimeMillis)
                .password("password" + currentTimeMillis).build();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
