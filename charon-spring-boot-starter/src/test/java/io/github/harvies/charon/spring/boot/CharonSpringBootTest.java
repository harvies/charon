package io.github.harvies.charon.spring.boot;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;

public class CharonSpringBootTest extends BaseTest {
    @Value("${spring.jackson.time-zone:11}")
    private String property;

    @Resource
    private Environment environment;

    @Test
    public void test() {
        String property = environment.getProperty("spring.jackson.time-zone");
        Assert.assertEquals("GMT+8", property);
    }
}
