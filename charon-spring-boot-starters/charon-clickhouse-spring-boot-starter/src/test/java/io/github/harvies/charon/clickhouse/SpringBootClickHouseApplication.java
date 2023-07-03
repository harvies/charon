package io.github.harvies.charon.clickhouse;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * @author harvies
 */
@SpringBootApplication
@Slf4j
public class SpringBootClickHouseApplication implements CommandLineRunner {

    @Resource
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootClickHouseApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println(("----- selectAll method test ------"));
        List<Map<String, Object>> queryForList = jdbcTemplate.queryForList("show tables;");
        queryForList.forEach(System.out::println);
    }
}
