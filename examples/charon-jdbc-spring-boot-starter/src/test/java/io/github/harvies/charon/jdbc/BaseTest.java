package io.github.harvies.charon.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * 神坑：@TestPropertySource(locations="xxx) 不支持yaml文件
 * @link <a href="https://github.com/spring-projects/spring-boot/issues/10772">...</a>
 */
@ExtendWith(value = SpringExtension.class)
@SpringBootTest(classes = CharonJdbcSpringBootApplication.class)
@TestPropertySource(properties = {"spring.config.location = classpath:application-shardingsphere.yml"})
@Slf4j
public abstract class BaseTest {
}
