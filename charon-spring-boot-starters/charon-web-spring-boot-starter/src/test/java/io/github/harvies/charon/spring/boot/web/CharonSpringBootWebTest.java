package io.github.harvies.charon.spring.boot.web;

import org.junit.Assert;
import org.junit.Test;
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
    public void test() {
        String forObject = restTemplateBuilder.build().getForObject(host + port + "/health/status", String.class);
        Assert.assertEquals(Constants.SUCCESS, forObject);
    }

}
