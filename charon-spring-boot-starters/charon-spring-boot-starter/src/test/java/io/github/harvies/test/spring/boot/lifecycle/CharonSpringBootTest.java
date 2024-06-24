package io.github.harvies.test.spring.boot.lifecycle;

import io.github.harvies.test.spring.boot.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

import jakarta.annotation.Resource;

public class CharonSpringBootTest extends BaseTest {
    @Value("${spring.jackson.time-zone:11}")
    private String property;

    @Resource
    private Environment environment;

    @Test
    public void test() {
        String property = environment.getProperty("spring.jackson.time-zone");
        Assert.assertEquals("GMT+9", property);
    }
}
