package io.github.harvies.charon.elasticsearch;

import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchAllQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.TermsQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.TermsQueryField;
import com.alibaba.fastjson2.JSON;
import io.github.harvies.charon.util.FileUtils;
import io.github.harvies.charon.util.JsonUtils;
import jakarta.annotation.Resource;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.IndicesTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.client.elc.QueryBuilders;
import org.springframework.data.elasticsearch.client.erhlc.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.client.erhlc.NativeSearchQuery;
import org.springframework.data.elasticsearch.client.erhlc.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.*;
import org.springframework.data.elasticsearch.core.cluster.ClusterHealth;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.IndexQuery;

import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
        deleteIndex();
        doCreateIndex();
        deleteIndex();
    }

    @SneakyThrows
    private void doCreateIndex() {
        IndexOperations indexOperations = elasticsearchOperations.indexOps(IndexCoordinates.of(indexName));
        indexOperations.create(Document.parse(
                FileUtils.readClassPathFile("index/user/user_settings.json", "UTF-8")
        ));
    }

    @SneakyThrows
    @Test
    void putMapping() {
        deleteIndex();
        doCreateIndex();
        IndexOperations indexOperations = elasticsearchOperations.indexOps(IndexCoordinates.of(indexName));
        indexOperations.putMapping(Document.parse(
                FileUtils.readClassPathFile("index/user/user_mapping.json", "UTF-8")
        ));
        deleteIndex();
    }

    @Test
    void index() {
        doCreateIndex();
        doIndex();
        deleteIndex();
    }

    private void doIndex() {
        User user = new User()
                .setId(1L)
                .setUsername("user1")
                .setPassword("pass1")
                .setTagList(Arrays.asList("123", "345"))
                .setGmtCreate(new Date().getTime())
                .setDescription("世界你好");
        IndexQuery indexQuery = new IndexQuery();
        indexQuery.setId("1");
        indexQuery.setSource(JsonUtils.toJSONString(user));
        elasticsearchOperations.index(indexQuery, IndexCoordinates.of(indexName));
    }

    @Test
    void criteriaQuery() {
        doCreateIndex();
        doIndex();
        CriteriaQuery criteriaQuery = new CriteriaQuery(Criteria.where("tagList").in("123", "234", "345"));
        SearchHits<User> hits = elasticsearchOperations.search(criteriaQuery, User.class, IndexCoordinates.of(indexName));
        List<SearchHit<User>> searchHits = hits.getSearchHits();
        log.info("searchHits:[{}]", JSON.toJSONString(searchHits));
        deleteIndex();
    }

    @Test
    void nativeSearchQuery() {
        doCreateIndex();
        doIndex();
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
        log.info("searchHits:[{}]", JSON.toJSONString(searchHits));
        deleteIndex();
    }

    @Test
    void delete() {
        doCreateIndex();
        doIndex();
        elasticsearchOperations.delete("1", IndexCoordinates.of(indexName));
        deleteIndex();
    }


    @SneakyThrows
    private void doRefresh() {
        // TODO: 2023/7/4
        TimeUnit.SECONDS.sleep(2);
    }

    @Test
    public void scrollSearch() {
        createIndex();
        doIndex();
        doRefresh();
        // 创建查询条件和分页信息
        MatchAllQuery matchAllQuery = new MatchAllQuery.Builder().build();
        NativeQuery nativeQuery = new NativeQuery(matchAllQuery._toQuery());
        // 执行滚动查询
        @Cleanup
        SearchHitsIterator<User> scrolledPage = elasticsearchOperations.searchForStream(nativeQuery, User.class, IndexCoordinates.of(indexName));
        // 迭代查询结果
        while (scrolledPage.hasNext()) {
            SearchHit<User> userSearchHit = scrolledPage.next();
            log.info("userSearchHit:[{}]", JSON.toJSONString(userSearchHit));
        }
        deleteIndex();
    }

}
