package io.github.harvies.charon.spring.boot.web;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

public class CharonSpringBootWebTest extends BaseTest {
    @Resource
    private RestTemplateBuilder restTemplateBuilder;

    @Test
    public void test() {
        List<HttpMessageConverter<?>> messageConverters = restTemplateBuilder.build().getMessageConverters();
        Assert.assertNotNull(messageConverters);
    }
}
