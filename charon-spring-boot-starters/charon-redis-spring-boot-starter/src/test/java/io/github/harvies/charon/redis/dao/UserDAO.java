package io.github.harvies.charon.redis.dao;

import io.github.harvies.charon.redis.dto.UserDTO;

import java.util.List;

public interface UserDAO {

    UserDTO get(Long id);

    List<UserDTO> list();

    void save(UserDTO userDTO);
}
