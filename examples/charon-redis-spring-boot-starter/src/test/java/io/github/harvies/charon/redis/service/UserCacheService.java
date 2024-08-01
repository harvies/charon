package io.github.harvies.charon.redis.service;

import io.github.harvies.charon.redis.dto.UserDTO;

import java.util.List;

public interface UserCacheService {

    UserDTO get(Long id);

    List<UserDTO> list();

    void initDB();
}
