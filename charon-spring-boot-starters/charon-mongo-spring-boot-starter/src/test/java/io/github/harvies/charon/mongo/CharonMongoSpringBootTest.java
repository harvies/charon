package io.github.harvies.charon.mongo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CharonMongoSpringBootTest extends BaseTest {

    @Resource
    private MongoTemplate mongoTemplate;

    @Test
    public void test() {
        List<String> collectionNameList = new ArrayList<>();
        mongoTemplate.getDb().listCollectionNames().forEach(s -> collectionNameList.add(s));
        Assertions.assertNotEquals(0, collectionNameList.size());
    }
}
