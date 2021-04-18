package io.github.harvies.charon.shardingsphere.jdbc;

import io.github.harvies.charon.shardingsphere.jdbc.entity.Order;
import io.github.harvies.charon.shardingsphere.jdbc.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;

@Slf4j
class CharonShardingSphereJdbcSpringBootTest extends BaseTest {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private OrderMapper orderMapper;

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
    void createTableIfNotExists() {
        orderMapper.createTableIfNotExists();
    }

    @Test
    void truncateTable() {
        orderMapper.truncateTable();
    }

    @Test
    void dropTable() {
        orderMapper.dropTable();
    }

}
