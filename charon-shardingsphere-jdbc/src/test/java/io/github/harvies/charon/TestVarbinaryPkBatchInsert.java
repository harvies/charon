package io.github.harvies.charon;

import org.apache.shardingsphere.driver.ShardingSphereDriver;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestVarbinaryPkBatchInsert {


    @Test
    public void createTable() throws SQLException {
        String ddl = """
                CREATE TABLE `t_order` (
                  `order_id` varbinary(64) NOT NULL,
                  `user_id` int(11) NOT NULL,
                  `status` varchar(255) DEFAULT NULL,
                  PRIMARY KEY (`order_id`)
                ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
                """;
        new ShardingSphereDriver();
        try (Connection connection = DriverManager.getConnection("jdbc:shardingsphere:classpath:driver/test_varbinary_pk_batch_insert.yaml");
             PreparedStatement statement = connection.prepareStatement(ddl)) {
            connection.setAutoCommit(false);
            statement.executeUpdate();
            connection.commit();
        }
    }

    @Test
    public void test1() throws SQLException {
        new ShardingSphereDriver();
        try (Connection connection = DriverManager.getConnection("jdbc:shardingsphere:classpath:driver/test_varbinary_pk_batch_insert.yaml");
             PreparedStatement statement = connection.prepareStatement("INSERT INTO t_order (order_id,user_id,status) VALUES (?,?,?)")) {
            connection.setAutoCommit(false);
            statement.setObject(1, "a1".getBytes());
            statement.setObject(2, 101);
            statement.setObject(3, "ok");
//            statement.addBatch();
//            statement.executeBatch();
            statement.executeUpdate();
            connection.commit();
        }
    }
}