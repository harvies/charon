package io.github.harvies.charon.redis.dao.impl;

import io.github.harvies.charon.redis.dao.UserDAO;
import io.github.harvies.charon.redis.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class UserDAOImpl implements UserDAO {

    private static final String USER_CACHE_MAP_KEY = "user:cache:map";

    @Resource
    private RedissonClient redissonClient;

    @Override
    public UserDTO get(Long id) {
        log.info("get user from db id:[{}]", id);
        return redissonClient.<Long, UserDTO>getMap(USER_CACHE_MAP_KEY).get(id);
    }

    @Override
    public List<UserDTO> list() {
        return new ArrayList<>(redissonClient.<Long, UserDTO>getMap(USER_CACHE_MAP_KEY).values());
    }

    @Override
    public void save(UserDTO userDTO) {
        redissonClient.<Long, UserDTO>getMap(USER_CACHE_MAP_KEY).put(userDTO.getId(), userDTO);
    }
}
