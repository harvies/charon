package io.github.harvies.charon.elasticsearch;

import io.github.harvies.charon.util.PropertiesUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Node;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

@Slf4j
public class ElasticSearchRestTest {

    private RestClient restClient;

    @BeforeEach
    public void beforeEach() {
        String uris = PropertiesUtils.getDefaultProperty("charon.elasticsearch.rest.uris");
        HttpHost httpHost = HttpHost.create("http://" + uris);
        restClient = RestClient.builder(httpHost)
                .setRequestConfigCallback(requestConfigBuilder -> {
                    //建立连接
                    requestConfigBuilder.setConnectTimeout(5000);
                    //读取数据
                    requestConfigBuilder.setSocketTimeout(10000);
                    //获取连接
                    requestConfigBuilder.setConnectionRequestTimeout(3000);
                    return requestConfigBuilder;
                })
                .build();
        List<Node> nodes = restClient.getNodes();
        log.info("nodes:[{}]", nodes);
    }

    @Test
    public void _count() throws IOException {
        Request request = new Request("POST", "blog/_count");
        Response response = restClient.performRequest(request);
        System.out.println(response);
        String result = EntityUtils.toString(response.getEntity(), "UTF-8");
        System.out.println(result);
    }

    @Test
    public void _search() throws IOException {
        Request request = new Request("POST", "blog/_search");
        Response response = restClient.performRequest(request);
        System.out.println(response);
        String result = EntityUtils.toString(response.getEntity(), "UTF-8");
        System.out.println(result);
    }

    @AfterEach
    public void afterEach() throws IOException {
        restClient.close();
    }
}
