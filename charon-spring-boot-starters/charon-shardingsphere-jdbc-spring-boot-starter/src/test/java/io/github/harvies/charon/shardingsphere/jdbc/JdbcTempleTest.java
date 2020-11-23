package io.github.harvies.charon.shardingsphere.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
public class JdbcTempleTest extends BaseTest {
    @Resource
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void createTable() {
        jdbcTemplate.execute("DROP TABLE IF EXISTS t_customer");
        jdbcTemplate.execute("CREATE TABLE t_customer(" +
                "id bigint(20), first_name VARCHAR(255), last_name VARCHAR(255))");
    }

    @Test
    public void insert() {
        for (int i = 0; i < 17; i++) {
            int update = jdbcTemplate.update("INSERT INTO t_customer(id,first_name, last_name) VALUES (?,?,?)", i,"first_name_" + i, "last_name_" + i);
            log.warn("update rows:{}", update);
        }

    }

    @Test
    public void selectAll() {
        jdbcTemplate.query("SELECT id, first_name, last_name FROM t_customer", rs -> {
            Customer build = Customer.builder()
                    .id(rs.getLong(1))
                    .firstName(rs.getString(2))
                    .lastName(rs.getString(3))
                    .build();
            log.warn("result:{}", build);
        });
    }

    @Test
    public void selectAllByFirstName() {
        List<Customer> customerList = jdbcTemplate.queryForList("SELECT id, first_name, last_name FROM t_customer where first_name = ?", Customer.class, "first_name_1");
        System.err.println(customerList);
    }

}
