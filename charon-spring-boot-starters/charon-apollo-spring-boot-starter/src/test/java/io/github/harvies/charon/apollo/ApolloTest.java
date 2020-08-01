package io.github.harvies.charon.apollo;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

public class ApolloTest extends BaseTest {
    @Value("${test:1}")
    private String property;

    @Test
    public void test() {
    }
}
