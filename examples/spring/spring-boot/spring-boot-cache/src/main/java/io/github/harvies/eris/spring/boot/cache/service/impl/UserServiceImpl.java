package io.github.harvies.eris.spring.boot.cache.service.impl;


import com.google.common.collect.Lists;
import io.github.harvies.eris.spring.boot.cache.model.User;
import io.github.harvies.eris.spring.boot.cache.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * todo 需设置失效策略
 *
 * @author harvies
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private static Map<Long, User> userMap = new HashMap<>();

    static {
        for (long i = 0; i < 100; i++) {
            userMap.put(i, User.builder().id(i).username("zhangsan" + i).password("password" + i).build());
        }
    }

    @Override
    @Cacheable(value = "userService-getById")
    public User getById(Long id) {
        log.warn("getById id:{}", id);
        return userMap.get(id);
    }

    @Override
    @Cacheable(value = "userService-getByIdList")
    public List<User> getByIdList(List<Long> idList) {
        log.warn("getByIdList idList:{}", idList);
        List<User> userList = Lists.newArrayList();
        idList.forEach(aLong -> userList.add(userMap.get(aLong)));
        return userList;
    }
}
