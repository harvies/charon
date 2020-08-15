package io.github.harvies.charon.spring.boot.admin.server;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

public class SpringBootAdminTest extends BaseTest {
    @Value("${logging.level.root}")
    private String property;

    @Test
    public void test() {
        System.err.println(property);
    }
}
