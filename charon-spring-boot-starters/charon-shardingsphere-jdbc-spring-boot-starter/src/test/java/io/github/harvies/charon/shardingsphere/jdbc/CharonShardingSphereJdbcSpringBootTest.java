package io.github.harvies.charon.shardingsphere.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Slf4j
public class CharonShardingSphereJdbcSpringBootTest extends BaseTest {

    @Resource
    private DataSource dataSource;

    @Test
    public void test() {
        Assertions.assertNotNull(dataSource);
    }
}
