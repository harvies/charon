package io.github.harvies.charon.jpa;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@Slf4j
public class JpaAutoConfiguration {

    @Resource
    private EntityManager entityManager;

    @PostConstruct
    public void init() {
        log.info("JpaAutoConfiguration inited");
    }

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        log.info("JPAQueryFactory inited");
        return new JPAQueryFactory(entityManager);
    }

}
