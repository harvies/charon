package io.github.harvies.charon.shardingsphere.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Slf4j
public class CharonShardingSphereJdbcSpringBootTest extends BaseTest {

    @Resource
    private DataSource dataSource;
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Test
    public void test() {
        Assertions.assertNotNull(dataSource);
    }

    @Test
    public void jdbcTemplate() {
        System.out.println(jdbcTemplate.queryForList("show databases"));
    }
}
