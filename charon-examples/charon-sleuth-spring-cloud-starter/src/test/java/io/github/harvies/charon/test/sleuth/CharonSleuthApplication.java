package io.github.harvies.charon.test.sleuth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@Slf4j
public class CharonSleuthApplication {

    @RequestMapping("/")
    public String home() {
        log.info("Handling home");
        return "Hello World";
    }

    public static void main(String[] args) {
        SpringApplication.run(CharonSleuthApplication.class, args);
    }

}