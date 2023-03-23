package io.github.harvies.test.spring.boot.web;

import com.alibaba.fastjson.JSONObject;
import io.github.harvies.charon.util.JsonUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class ExceptionControllerTest extends BaseTest {
    @LocalServerPort
    private int port;

    protected static String host = "http://localhost:";

    @Resource
    private RestTemplateBuilder restTemplateBuilder;

    @Test
    void assertGlobalExceptionHandler() {
        String forObject = restTemplateBuilder.build().getForObject(host + port + "/exception/global", String.class);
        JSONObject jsonObject = JsonUtils.parseObject(forObject);
        assertEquals(TestExceptionController.RESULT, jsonObject.getString("data"));
    }
}
