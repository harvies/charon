package io.github.harvies.charon.shardingsphere.jdbc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.harvies.charon.shardingsphere.jdbc.entity.User;

public interface UserMapper extends BaseMapper<User> {

    void createTableIfNotExists();

    void truncateTable();

    void dropTable();
}
