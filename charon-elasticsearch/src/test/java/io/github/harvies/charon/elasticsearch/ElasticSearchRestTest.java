package io.github.harvies.charon.elasticsearch;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.junit.jupiter.api.Test;

import java.io.IOException;

@Slf4j
public class ElasticSearchRestTest extends BaseTest {


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

}
