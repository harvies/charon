package io.github.harvies.test.web;

import io.github.harvies.charon.spring.boot.web.Constants;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CharonSpringBootWebTest extends BaseTest {

    @LocalServerPort
    private int port;

    protected static String host = "http://localhost:";

    @Resource
    private RestTemplateBuilder restTemplateBuilder;

    @Test
    void testHealthStatus() {
        String forObject = restTemplateBuilder.build().getForObject(host + port + "/health/status?a=b", String.class);
        assertEquals(Constants.SUCCESS, forObject);
    }
}
