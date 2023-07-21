package io.github.harvies.charon.jdbc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("io.github.harvies.charon.jdbc.mapper")
public class CharonJdbcSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(CharonJdbcSpringBootApplication.class, args);
    }
}
