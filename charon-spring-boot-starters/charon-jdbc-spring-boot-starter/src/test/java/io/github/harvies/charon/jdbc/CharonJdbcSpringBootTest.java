package io.github.harvies.charon.jdbc;

import io.github.harvies.charon.jdbc.mapper.UserMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class CharonJdbcSpringBootTest extends BaseTest {

    @Resource
    private UserMapper userMapper;

    @Test
    void createTableIfNotExists() {
        userMapper.createTableIfNotExists();
    }

    @Test
    void truncateTable() {
        userMapper.truncateTable();
    }

    @Test
    void dropTable() {
        userMapper.dropTable();
    }

}
