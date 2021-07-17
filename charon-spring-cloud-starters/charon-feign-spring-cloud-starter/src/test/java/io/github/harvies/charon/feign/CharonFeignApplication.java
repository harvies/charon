package io.github.harvies.charon.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CharonFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(CharonFeignApplication.class, args);
    }

}