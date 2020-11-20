package io.github.harvies.charon.elasticsearch;

import io.github.harvies.charon.util.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;

@Slf4j
public class NestedTest extends BaseTest {

    private String indexName = "blog";

    @Test
    public void dropIndex() throws Exception {
        Request request = new Request("DELETE", indexName);
        Response response = restClient.performRequest(request);
        System.out.println(response);
    }

    @Test
    public void mapping() throws Exception {
        Request request = new Request("PUT", indexName);
        String json = FileUtils.readClassPathFile("mappings/nested/mappings.json", "UTF-8");
        request.setJsonEntity(json);
        Response response = restClient.performRequest(request);
        System.out.println(response);
    }

    @Test
    public void index() throws Exception {
        Request request = new Request("PUT", indexName + "/_doc/1");
        String json = FileUtils.readClassPathFile("mappings/nested/doc/1.json", "UTF-8");
        request.setJsonEntity(json);
        Response response = restClient.performRequest(request);
        System.out.println(response);
    }

    @Test
    public void index2() throws Exception {
        Request request = new Request("PUT", indexName + "/_doc/2");
        String json = FileUtils.readClassPathFile("mappings/nested/doc/2.json", "UTF-8");
        request.setJsonEntity(json);
        Response response = restClient.performRequest(request);
        System.out.println(response);
    }

    @Test
    public void search() throws Exception {
        Request request = new Request("POST", indexName + "/_search");
        String json = FileUtils.readClassPathFile("mappings/nested/search/1.json", "UTF-8");
        request.setJsonEntity(json);
        Response response = restClient.performRequest(request);
        System.out.println(response);
        String result = EntityUtils.toString(response.getEntity(), "UTF-8");
        System.out.println(result);
    }

}
