package io.github.harvies.charon.eureka.server;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author harvies
 */
@SpringBootApplication
@EnableEurekaServer
public class CharonEurekaServerApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(CharonEurekaServerApplication.class).run(args);
    }

}
