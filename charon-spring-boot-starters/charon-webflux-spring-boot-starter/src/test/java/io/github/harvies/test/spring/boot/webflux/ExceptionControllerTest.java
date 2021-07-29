package io.github.harvies.test.spring.boot.webflux;

import com.alibaba.fastjson.JSONObject;
import io.github.harvies.charon.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;

import javax.annotation.Resource;

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
