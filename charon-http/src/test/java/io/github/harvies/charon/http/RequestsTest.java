package io.github.harvies.charon.http;

import lombok.extern.slf4j.Slf4j;
import net.dongliu.requests.Proxies;
import net.dongliu.requests.RawResponse;
import net.dongliu.requests.Requests;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author harvies
 */
@Slf4j
public class RequestsTest {
    @Test
    public void get() {
        RawResponse rawResponse = Requests.get("https://baidu.com").send();
        log.info("rawResponse:[{}]", rawResponse);
        Assert.assertNotNull(rawResponse.readToText());
    }

    @Test
    public void testSocksProxy() {
        RawResponse rawResponse = Requests.get("http://ip-api.com/json/?fields=query").timeout(1000 * 60 * 60).proxy(Proxies.socksProxy("127.0.0.1", 1080)).send();
        Assert.assertNotNull(rawResponse.readToText());
    }
}
