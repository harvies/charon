package io.github.harvies.charon.jdbc;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.github.harvies.charon.jdbc.mapper.SystemMapper;
import io.github.harvies.charon.jdbc.mapper.UserMapper;
import io.github.harvies.charon.jdbc.po.UserPO;
import io.github.harvies.charon.util.RandomUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Slf4j
class CharonJdbcSpringBootTest extends BaseTest {

    @Resource
    private UserMapper userMapper;

    @Resource
    private SystemMapper systemMapper;

    @Test
    void showDatabases() {
        List<String> databaseList = systemMapper.showDatabases();
        log.info("databaseList:[{}]", JSON.toJSONString(databaseList));
    }

    @Test
    void test0() {
        userMapper.dropTableIfExists();
        userMapper.createTableIfNotExists();
        for (int i = 0; i < 10; i++) {
            UserPO userPO = new UserPO();
            userPO.setUsername(String.valueOf(RandomUtils.nextInt(100000, 999999)));
            userPO.setMobile(String.valueOf(RandomUtils.nextInt(1000000, 9999999)));
            userMapper.insert(userPO);
            String key = "key_" + userPO.getId();
            String value = "value_" + userPO.getId();
            userMapper.putJson("features", userPO.getId(), key, value);
            assertThat(userMapper.selectById(userPO.getId()).getFeatures().get(key), is(value));
        }
        Long count = userMapper.selectCount(Wrappers.emptyWrapper());
        log.info("count:[{}]", count);
        assertThat(count, is(10L));
        userMapper.truncateTable();
        userMapper.dropTableIfExists();
    }

}
