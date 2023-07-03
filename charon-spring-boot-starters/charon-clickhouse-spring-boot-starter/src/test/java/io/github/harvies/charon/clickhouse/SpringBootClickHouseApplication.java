package io.github.harvies.charon.clickhouse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.util.List;

/**
 * @author harvies
 */
@SpringBootApplication
@Slf4j
public class SpringBootClickHouseApplication implements CommandLineRunner {

    /**
     * \@Autowired 按类型注入 @Resource按名称注入
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootClickHouseApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println(("----- selectAll method test ------"));
        List<ResultSet> resultSets = jdbcTemplate.query("select * from rss_item_202307", (rs, rowNum) -> {
            return rs;
        });
        resultSets.forEach(System.out::println);
    }
}
