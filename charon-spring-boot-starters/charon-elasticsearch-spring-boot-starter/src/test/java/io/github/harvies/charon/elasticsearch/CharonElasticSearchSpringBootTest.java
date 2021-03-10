package io.github.harvies.charon.elasticsearch;

import io.github.harvies.charon.util.FileUtils;
import io.github.harvies.charon.util.JsonUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;

import javax.annotation.Resource;

@Slf4j
public class CharonElasticSearchSpringBootTest extends BaseTest {

    private static final String indexName = "test0310";

    @Resource
    private ElasticsearchOperations elasticsearchOperations;

    @Test
    public void elasticsearchOperations() {
        Assertions.assertNotNull(elasticsearchOperations);
    }

    @Test
    void deleteIndex() {
        elasticsearchOperations.indexOps(IndexCoordinates.of(indexName)).delete();
    }

    @SneakyThrows
    @Test
    void createIndex() {
        IndexOperations indexOperations = elasticsearchOperations.indexOps(IndexCoordinates.of(indexName));
        indexOperations.create(Document.parse(
                FileUtils.readClassPathFile("index/user/user_settings.json", "UTF-8")
        ));
    }

    @SneakyThrows
    @Test
    void putMapping() {
        IndexOperations indexOperations = elasticsearchOperations.indexOps(IndexCoordinates.of(indexName));
        indexOperations.putMapping(Document.parse(
                FileUtils.readClassPathFile("index/user/user_mapping.json", "UTF-8")
        ));
    }

    @Test
    void index() {
        User user = new User()
                .setId(1L)
                .setUsername("user1")
                .setPassword("pass1");
        IndexQuery indexQuery = new IndexQuery();
        indexQuery.setId("1");
        indexQuery.setSource(JsonUtils.toJSONString(user));
        elasticsearchOperations.index(indexQuery, IndexCoordinates.of(indexName));
    }

    @Test
    void delete() {
        elasticsearchOperations.delete("1", IndexCoordinates.of(indexName));
    }
}
