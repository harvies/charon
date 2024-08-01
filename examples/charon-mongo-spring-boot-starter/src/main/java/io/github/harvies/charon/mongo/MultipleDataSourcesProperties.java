package io.github.harvies.charon.mongo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * mongo多数据源配置
 */
@Getter
@Setter
public class MultipleDataSourcesProperties {

    /**
     * 数据源map
     */
    private Map<String, MongoProperties> dataSources = new LinkedHashMap<>();
}