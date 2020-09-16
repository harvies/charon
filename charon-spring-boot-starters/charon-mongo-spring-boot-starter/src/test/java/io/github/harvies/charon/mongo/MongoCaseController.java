package io.github.harvies.charon.mongo;

import com.mongodb.client.MongoDatabase;
import io.github.harvies.charon.spring.boot.web.result.ResultDTO;
import io.github.harvies.charon.spring.boot.web.result.Results;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author harvies
 */
@RestController
@RequestMapping(value = "/mongo", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class MongoCaseController {


    @Resource
    //io.github.harvies.charon.mongo.MongoAutoConfiguration.init执行晚于bean注入
    @Lazy
    private List<MongoTemplate> mongoTemplateList;

    @RequestMapping("/case")
    public ResultDTO<Boolean> mongodb() {
        for (MongoTemplate mongoTemplate : mongoTemplateList) {
            MongoDatabase mongoDatabase = mongoTemplate.getDb();
            mongoDatabase.listCollectionNames().forEach(s -> log.info("{} dbName:[{}] collectionNames:[{}]", mongoTemplate.hashCode(), mongoDatabase.getName(), s));
            log.info("--------------------");
        }
        return Results.success(true);
    }
}
