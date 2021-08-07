package io.github.harvies.charon.elasticsearch;

import io.github.harvies.charon.elasticsearch.config.ElasticSearchConfig;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
@ImportAutoConfiguration(value = {ElasticSearchConfig.class})
public class CharonElasticSearchAutoConfiguration {
}
