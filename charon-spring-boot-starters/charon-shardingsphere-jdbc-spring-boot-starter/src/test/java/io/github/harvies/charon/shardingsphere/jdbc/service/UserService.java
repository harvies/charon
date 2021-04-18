package io.github.harvies.charon.shardingsphere.jdbc.service;

import io.github.harvies.charon.shardingsphere.jdbc.entity.User;

public interface UserService {

    User selectById(Long id);

    int updateById(User user);

    void migrate(Long from, Long to);
}
