package io.github.harvies.test.spring.boot.web;

import io.github.harvies.charon.spring.boot.web.Constants;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;

class CharonSpringBootWebTest extends BaseTest {

    @LocalServerPort
    private int port;

    protected static String host = "http://localhost:";

    @Resource
    private RestTemplateBuilder restTemplateBuilder;

    @Test
    void testHealthStatus() {
        String forObject = restTemplateBuilder.build().getForObject(host + port + "/health/status?a=b", String.class);
        Assertions.assertEquals(Constants.SUCCESS, forObject);
    }
}
