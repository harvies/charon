package io.github.harvies.charon.mongo;

import com.mongodb.client.MongoDatabase;
import io.github.harvies.charon.model.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * @author harvies
 */
@RestController
@RequestMapping(value = "/mongo", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class MongoCaseController {


    @Resource
    private List<MongoTemplate> mongoTemplateList;

    @RequestMapping("/case")
    public ApiResult<Boolean> mongodb() {
        for (MongoTemplate mongoTemplate : mongoTemplateList) {
            MongoDatabase mongoDatabase = mongoTemplate.getDb();
            mongoDatabase.listCollectionNames().forEach(s -> log.info("{} dbName:[{}] collectionNames:[{}]", mongoTemplate.hashCode(), mongoDatabase.getName(), s));
            log.info("--------------------");
        }
        return ApiResult.success(true);
    }
}
