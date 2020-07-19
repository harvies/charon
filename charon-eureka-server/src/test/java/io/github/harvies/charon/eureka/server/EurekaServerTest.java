package io.github.harvies.charon.eureka.server;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

public class EurekaServerTest extends BaseTest {
    @Value("${eureka.client.serviceUrl.defaultZone:1}")
    private String property;

    @Test
    public void test() {
        Assert.assertEquals("http://localhost:8761/eureka/", property);
    }
}
