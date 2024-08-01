package io.github.harvies.charon.elasticsearch;

import io.github.harvies.charon.util.PropertiesUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.Node;
import org.elasticsearch.client.RestClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.util.List;

@Slf4j
public abstract class BaseTest {

    protected RestClient restClient;

    @BeforeEach
    public void beforeEach() {
        String uris = PropertiesUtils.getDefaultProperty("charon.elasticsearch.rest.uris");
        String username = PropertiesUtils.getDefaultProperty("charon.elasticsearch.rest.username");
        String password = PropertiesUtils.getDefaultProperty("charon.elasticsearch.rest.password");
        HttpHost httpHost = HttpHost.create(uris);
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));
        restClient = RestClient.builder(httpHost)
                .setHttpClientConfigCallback(httpClientBuilder -> {
                    httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                    return httpClientBuilder;
                })
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

    @AfterEach
    public void afterEach() throws IOException {
        restClient.close();
    }
}
