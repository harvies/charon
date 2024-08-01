package io.github.harvies.charon.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(value = SpringExtension.class)
@SpringBootTest(classes = CharonRocketMQSpringBootApplication.class)
@Slf4j
public abstract class BaseTest {
}
