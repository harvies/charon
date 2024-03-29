package io.github.harvies.test.spring.boot.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;

@SpringBootApplication
public class CharonSpringBootWebApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(CharonSpringBootWebApplication.class);
        //https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.spring-application.startup-tracking
        app.setApplicationStartup(new BufferingApplicationStartup(2048));
        app.run(args);
    }
}
