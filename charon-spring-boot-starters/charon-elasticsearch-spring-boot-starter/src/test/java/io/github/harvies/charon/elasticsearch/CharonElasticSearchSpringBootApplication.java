package io.github.harvies.charon.elasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "io.github.harvies.charon.elasticsearch.repository")
public class CharonElasticSearchSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(CharonElasticSearchSpringBootApplication.class, args);
    }
}
