package io.github.harvies.charon.spring.boot.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.locks.LockSupport;


@SpringBootApplication
public class CharonSpringBootWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(CharonSpringBootWebApplication.class, args);
        LockSupport.park();
    }
}
