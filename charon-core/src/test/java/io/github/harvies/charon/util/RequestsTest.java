package io.github.harvies.charon.util;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import lombok.extern.slf4j.Slf4j;
import net.dongliu.requests.Proxies;
import net.dongliu.requests.RawResponse;
import net.dongliu.requests.Requests;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author harvies
 */
@Slf4j
public class RequestsTest {
    private Config config;
    private String dingTalkWebHookUrl;
    /**
     * server酱
     */
    private String serverSauceWebHookUrl;

    @BeforeEach
    public void before() {
        //load other properties
        System.setProperty("env", "dev");
        config = ConfigService.getConfig("dev-common");
        log.info("config {}", config.getPropertyNames());
        dingTalkWebHookUrl = config.getProperty("dingtalk.webhook", "");
        serverSauceWebHookUrl = config.getProperty("serverSauceWebHookUrl", "");
    }

    @Test
    public void get() {
        RawResponse rawResponse = Requests.get("https://baidu.com").send();
        log.info("rawResponse:[{}]", ToStringBuilder.reflectionToString(rawResponse, ToStringStyle.JSON_STYLE));
        Assertions.assertNotNull(rawResponse.readToText());
    }

    @Disabled
    public void testSocksProxy() {
        RawResponse rawResponse = Requests.get("http://ip-api.com/json/?fields=query").timeout(1000 * 60 * 60).proxy(Proxies.socksProxy("127.0.0.1", 1080)).send();
        Assertions.assertNotNull(rawResponse.readToText());
    }

    /**
     * server酱测试
     */
    @Test
    public void serverSauce() {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("text", "测试消息标题");
        paramMap.put("desp", "测试消息内容");
        String text = Requests.post(serverSauceWebHookUrl)
                .params(paramMap)
                .send().readToText();
        Assertions.assertNotNull(text);
    }


    /**
     * dingtalk
     */
    @Test
    public void dingtalk() {
        Map<String, Object> data = new HashMap<>();
        data.put("msgtype", "text");
        Map<String, Object> textObject = new HashMap<>();
        textObject.put("content", "报警-测试内容22211");
        data.put("text", textObject);
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Content-Type", "application/json");
        String text = Requests.post(dingTalkWebHookUrl)
                .body(JsonUtils.toJSONString(data))
                .headers(headerMap)
                .send().readToText();
        Assertions.assertNotNull(text);
    }
}
