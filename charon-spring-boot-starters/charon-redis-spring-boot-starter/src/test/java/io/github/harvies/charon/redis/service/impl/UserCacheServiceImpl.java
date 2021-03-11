package io.github.harvies.charon.redis.service.impl;

import io.github.harvies.charon.redis.dao.UserDAO;
import io.github.harvies.charon.redis.dto.UserDTO;
import io.github.harvies.charon.redis.service.UserCacheService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class UserCacheServiceImpl implements UserCacheService {

    private static final String USER_CACHE_ID_KEY_PREFIX = "user:cache:id_";

    private static final String LOCK_KEY = "user:lock";

    @Resource
    private UserDAO userDAO;

    @Resource
    private RedissonClient redissonClient;

    @Override
    public UserDTO get(Long id) {
        String cacheKey = USER_CACHE_ID_KEY_PREFIX + id;
        UserDTO userDTO = redissonClient.<UserDTO>getBucket(cacheKey).get();
        if (userDTO == null) {
            RLock lock = redissonClient.getLock(LOCK_KEY);
            try {
                lock.lock();
                UserDTO userDTONew = redissonClient.<UserDTO>getBucket(cacheKey).get();
                if (userDTONew == null) {
                    UserDTO userDTOFromDB = userDAO.get(id);
                    //模拟业务逻辑
                    try {
                        TimeUnit.MILLISECONDS.sleep(200);
                    } catch (InterruptedException e) {
                        log.error("sleep error", e);
                    }
                    redissonClient.<UserDTO>getBucket(cacheKey).set(userDTOFromDB, 10, TimeUnit.SECONDS);
                    return userDTOFromDB;
                }
                return userDTONew;
            } finally {
                lock.unlock();
            }
        }
        return userDTO;
    }

    @Override
    public List<UserDTO> list() {
        return userDAO.list();
    }

    @Override
    public void initDB() {
        for (long i = 1; i <= 10; i++) {
            UserDTO userDTO = new UserDTO()
                    .setId(i)
                    .setUsername("user" + i)
                    .setPassword("password" + i);
            userDAO.save(userDTO);
        }
    }
}
