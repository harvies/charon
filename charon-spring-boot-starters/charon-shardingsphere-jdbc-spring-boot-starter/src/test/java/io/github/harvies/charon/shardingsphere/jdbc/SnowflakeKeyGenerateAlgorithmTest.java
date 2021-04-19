package io.github.harvies.charon.shardingsphere.jdbc;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.sharding.algorithm.keygen.SnowflakeKeyGenerateAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Properties;

@Slf4j
class SnowflakeKeyGenerateAlgorithmTest {

    @SneakyThrows
    @Test
    void nextId() {
        SnowflakeKeyGenerateAlgorithm snowflakeKeyGenerateAlgorithm = new SnowflakeKeyGenerateAlgorithm();
        Properties properties = new Properties();
        properties.setProperty("max-vibration-offset", "10");
        properties.setProperty("worker-id", "123");
        snowflakeKeyGenerateAlgorithm.setProps(properties);
        snowflakeKeyGenerateAlgorithm.init();
        for (int i = 0; i < 100; i++) {
            Long id = (Long) snowflakeKeyGenerateAlgorithm.generateKey();
            Thread.sleep(100);
            print(id);
        }
    }

    @Test
    void name() {
        print(590644812790083585L);
    }

    private void print(long id) {
        int totalTableNum = 4;
        int databaseNum=2;
        log.info("id:[{}] db_{} table_{}", id, id % totalTableNum / databaseNum, id % totalTableNum);
    }

}
