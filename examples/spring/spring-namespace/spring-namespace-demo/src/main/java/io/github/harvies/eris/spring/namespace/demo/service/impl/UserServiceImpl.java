package io.github.harvies.eris.spring.namespace.demo.service.impl;

import io.github.harvies.eris.spring.namespace.demo.service.UserService;
import io.github.harvies.eris.spring.namespace.demo.model.User;

import java.util.HashMap;
import java.util.Map;

/**
 * @author harvies
 */
public class UserServiceImpl implements UserService {
    private static Map<Long, User> userMap = new HashMap<>();

    static {
        userMap.put(1L, User.builder().id(1L).username("zhangsan").password("zhangsan123").build());
        userMap.put(2L, User.builder().id(2L).username("lisi").password("lisi123").build());
        userMap.put(3L, User.builder().id(3L).username("wangwu").password("wangwu123").build());
    }

    @Override
    public User getById(Long id) {
        return userMap.get(id);
    }
}
