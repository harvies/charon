package io.github.harvies.charon.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("io.github.harvies.charon.jpa")
public class CharonJpaSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(CharonJpaSpringBootApplication.class, args);
    }
}
