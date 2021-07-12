package io.github.harvies.charon.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CharonGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(CharonGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                // 请求 http://localhost:8080/json 时会转发请求(header会新增Hello:World)到 http://ip-api.com/json
                .route(p -> p
                        .path("/json")
                        .filters(f -> f.addRequestHeader("Hello", "World"))
                        .uri("http://ip-api.com")
                )
                .build();
    }
}
