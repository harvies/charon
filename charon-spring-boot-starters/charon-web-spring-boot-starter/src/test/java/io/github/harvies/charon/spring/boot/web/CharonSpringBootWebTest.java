package io.github.harvies.charon.spring.boot.web;

import org.junit.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;

import javax.annotation.Resource;

public class CharonSpringBootWebTest extends BaseTest {
    @Resource
    private RestTemplateBuilder restTemplateBuilder;

    @Test
    public void test() {

    }
}
