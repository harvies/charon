package io.github.harvies.charon.jdbc;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.github.harvies.charon.jdbc.mapper.UserMapper;
import io.github.harvies.charon.jdbc.po.UserPO;
import io.github.harvies.charon.util.RandomUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
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

    @Test
    void insert() {
        for (int i = 0; i < 10; i++) {
            UserPO userPO = new UserPO();
            userPO.setUsername(String.valueOf(RandomUtils.nextInt(100000, 999999)));
            userPO.setMobile(String.valueOf(RandomUtils.nextInt(1000000, 9999999)));
            userMapper.insert(userPO);
        }
    }

    @Test
    void count() {
        Long count = userMapper.selectCount(Wrappers.emptyWrapper());
        log.info("count:[{}]", count);
    }

}
