package io.github.harvies.charon.spring.boot.test.web;

import io.github.harvies.charon.spring.boot.web.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;

import javax.annotation.Resource;

public class CharonSpringBootWebTest extends BaseTest {
    @LocalServerPort
    private int port;

    protected static String host = "http://localhost:";

    @Resource
    private RestTemplateBuilder restTemplateBuilder;

    @Test
    public void testHealthStatus() {
        String forObject = restTemplateBuilder.build().getForObject(host + port + "/health/status?a=b", String.class);
        Assertions.assertEquals(Constants.SUCCESS, forObject);
    }

    @Test
    public void testThreadDump() {
        String forObject = restTemplateBuilder.build().getForObject(host + port + "/thread/dumpAllThreads", String.class);
        Assertions.assertNotNull(forObject);
    }

}
