package io.github.harvies.charon.elasticsearch;

import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.query_dsl.TermsQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.TermsQueryField;
import com.alibaba.fastjson2.JSON;
import io.github.harvies.charon.util.FileUtils;
import io.github.harvies.charon.util.JsonUtils;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.cluster.ClusterHealth;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.IndexQuery;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Slf4j
class CharonElasticSearchSpringBootTest extends BaseTest {

    private static final String indexName = "test_user";

    @Resource
    private ElasticsearchOperations elasticsearchOperations;

    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    void elasticsearchOperations() {
        Assertions.assertNotNull(elasticsearchOperations);
    }

    @Test
    void health() {
        ClusterHealth health = elasticsearchTemplate.cluster().health();
        log.info("health:[{}]", JSON.toJSONString(health));
        Assertions.assertNotNull(health);
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
                .setPassword("pass1")
                .setTagList(Arrays.asList("123", "345"))
                .setGmtCreate(new Date())
                .setDescription("世界你好");
        IndexQuery indexQuery = new IndexQuery();
        indexQuery.setId("1");
        indexQuery.setSource(JsonUtils.toJSONString(user));
        elasticsearchOperations.index(indexQuery, IndexCoordinates.of(indexName));
    }

    @Test
    void index2() {
        User user = new User()
                .setId(2L)
                .setUsername("user2")
                .setPassword("pass2")
                .setTagList(Arrays.asList("234", "345"))
                .setGmtCreate(new Date())
                .setDescription("世界你好2");
        IndexQuery indexQuery = new IndexQuery();
        indexQuery.setId("2");
        indexQuery.setSource(JSON.toJSONString(user, "yyyy-MM-dd HH:mm:ss"));
        elasticsearchOperations.index(indexQuery, IndexCoordinates.of(indexName));
    }

    @Test
    void criteriaQuery() {
        CriteriaQuery criteriaQuery = new CriteriaQuery(Criteria.where("tagList").in("123", "234", "345"));
        SearchHits<User> hits = elasticsearchOperations.search(criteriaQuery, User.class, IndexCoordinates.of(indexName));
        List<SearchHit<User>> searchHits = hits.getSearchHits();
        System.out.println(searchHits);
    }

    @Test
    void nativeSearchQuery() {
        TermsQuery termsQuery = new TermsQuery.Builder().field("tagList")
                .terms(
                        new TermsQueryField.Builder().value(
                                Arrays.asList(
                                        FieldValue.of("123"),
                                        FieldValue.of("234"),
                                        FieldValue.of("345"))
                        ).build()
                ).build();
        NativeQuery nativeQuery = new NativeQuery(termsQuery._toQuery());
        SearchHits<User> hits = elasticsearchOperations.search(nativeQuery, User.class, IndexCoordinates.of(indexName));
        List<SearchHit<User>> searchHits = hits.getSearchHits();
        System.out.println(searchHits);
    }

    @Test
    void delete() {
        elasticsearchOperations.delete("1", IndexCoordinates.of(indexName));
    }
}
