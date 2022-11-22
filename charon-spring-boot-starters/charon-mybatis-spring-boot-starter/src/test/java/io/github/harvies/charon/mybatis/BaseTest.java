package io.github.harvies.charon.mybatis;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(value = SpringExtension.class)
@SpringBootTest(classes = SpringBootMybatisPlusApplication.class)
public abstract class BaseTest {
}
