package io.github.harvies.charon.util;

import lombok.extern.slf4j.Slf4j;
import net.dongliu.requests.Proxies;
import net.dongliu.requests.RawResponse;
import net.dongliu.requests.Requests;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author harvies
 */
@Slf4j
public class RequestsTest {
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
    public void ft() {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("text", "测试消息标题");
        paramMap.put("desp", "测试消息内容");
        String text = Requests.post("https://sc.ftqq.com/SCU44485T1f79bf13e556ea84f657b63ff70fa3005c62778e1bc6f.send")
                .params(paramMap)
                .send().readToText();
        Assertions.assertNotNull(text);
    }
}
