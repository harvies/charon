package io.github.harvies.charon.apollo;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

public class ApolloTest extends BaseTest {
    @Value("${test.property:1}")
    private String property;

    @Value("${skywalking.collector.backend_service:1}")
    private String swAgent;

    @Test
    public void test() {
        Assert.assertEquals("1", property);
        Assert.assertEquals("1", swAgent);
    }
}
