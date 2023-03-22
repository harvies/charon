package io.github.harvies.charon.mongo;

import com.mongodb.client.MongoDatabase;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.core.MongoTemplate;

import jakarta.annotation.Resource;
import java.util.List;

@Slf4j
public class CharonMongoSpringBootTest extends BaseTest {

    @Resource
    private List<MongoTemplate> mongoTemplateList;

    @Test
    public void listCollectionNames() {
        for (MongoTemplate mongoTemplate : mongoTemplateList) {
            MongoDatabase mongoDatabase = mongoTemplate.getDb();
            mongoDatabase.listCollectionNames().forEach(s -> log.info("{} dbName:[{}] collectionNames:[{}]", mongoTemplate.hashCode(), mongoDatabase.getName(), s));
            log.info("--------------------");
        }
    }
}
