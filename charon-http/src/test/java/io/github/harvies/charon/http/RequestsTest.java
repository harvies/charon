package io.github.harvies.charon.http;

import io.github.harvies.charon.util.FileUtils;
import io.github.harvies.charon.util.PropertiesUtils;
import lombok.extern.slf4j.Slf4j;
import net.dongliu.requests.RawResponse;
import net.dongliu.requests.Requests;
import net.dongliu.requests.Session;
import net.dongliu.requests.body.Part;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.InetSocketAddress;
import java.net.Proxy;
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

    @Test
    public void post() {
        String readToText = Requests.post(PropertiesUtils.getDefaultProperty("charon.oss.url"))
                .multiPartBody(
                        Part.file("file", new File(FileUtils.getCurrentUserHomePath() + "/baidu.png")))
                .send().readToText();
        System.err.println(readToText);
    }

    @Test
    public void google() {
        Proxy proxy = new Proxy(Proxy.Type.SOCKS, InetSocketAddress.createUnresolved("52.207.229.227", 9050));
        String readToText = Requests.get("https://www.google.com").proxy(proxy).send().readToText();
        System.out.println(readToText);
    }

    @Test
    public void rssHub() {
        String readToText = Requests.get("https://rsshub.app/guanggoo/index").send().readToText();
        System.out.println(readToText);
    }

    @Test
    void jira() {
        Session session = Requests.session();
        Map<String, String> params = new HashMap<>();
        params.put("os_username", "xxx");
        params.put("os_password", "xxx");
        String loginResult = session.post("https://issues.apache.org/jira/login.jsp")
                .params(params).send().readToText();
        System.out.println(loginResult);
        String result = session.get("https://issues.apache.org/jira/secure/ViewProfile.jspa").send().readToText();
        System.out.println(result);
    }
}
