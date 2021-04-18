package io.github.harvies.charon.shardingsphere.jdbc;

import io.github.harvies.charon.shardingsphere.jdbc.entity.Order;
import io.github.harvies.charon.shardingsphere.jdbc.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Slf4j
class CharonShardingSphereJdbcSpringBootTest extends BaseTest {

    @Resource
    private DataSource dataSource;
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private OrderMapper orderMapper;

    @Test
    void test() {
        Assertions.assertNotNull(dataSource);
    }

    @Test
    void init() {
        for (int i = 1; i <= 10; i++) {
            Order order = new Order();
            order.setOrderId(i);
            order.setStatus(i + "");
            order.setUserId(i);
            order.setAddressId(i);
            orderMapper.insert(order);
        }
    }


    @Test
    void createTable() {
        jdbcTemplate.execute("CREATE TABLE `t_user` (\n" +
                "  `id` int unsigned NOT NULL AUTO_INCREMENT,\n" +
                "  `nickname` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL,\n" +
                "  `mobile` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL,\n" +
                "  `gmt_create` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,\n" +
                "  `gmt_modified` datetime NOT NULL,\n" +
                "  PRIMARY KEY (`id`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;");
    }

    @Test
    void aaa() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS t_order (order_id BIGINT AUTO_INCREMENT, user_id INT NOT NULL, address_id BIGINT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_id));");
    }
    
    @Test
    void drop() {
        jdbcTemplate.execute("drop table t_order; ");
    }

    @Test
    void jdbcTemplate() {
        System.out.println(jdbcTemplate.queryForList("show databases"));
    }
}
