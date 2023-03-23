package io.github.harvies.charon.elasticsearch.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.transport.ElasticsearchTransport;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchProperties;
import org.springframework.boot.autoconfigure.elasticsearch.RestClientBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchConverter;
import org.springframework.data.elasticsearch.core.convert.MappingElasticsearchConverter;
import org.springframework.data.elasticsearch.core.mapping.ElasticsearchPersistentEntity;
import org.springframework.data.elasticsearch.core.mapping.ElasticsearchPersistentProperty;
import org.springframework.data.mapping.context.MappingContext;

import java.util.Date;

@Configuration
public class ElasticSearchConfig {
    @Bean
    ElasticsearchClient elasticsearchClient(ElasticsearchTransport transport) {
        return new ElasticsearchClient(transport).withTransportOptions(
                builder -> {
                    builder.addHeader("X-Elastic-Product", "charon");
                    return builder;
                }
        );
    }

    @Bean
    RestClientBuilderCustomizer restClientBuilderCustomizer(ElasticsearchProperties properties) {
        return new ElasticSearchConfig.DefaultRestClientBuilderCustomizer(properties);
    }

    public class DefaultRestClientBuilderCustomizer implements RestClientBuilderCustomizer {
        public DefaultRestClientBuilderCustomizer(ElasticsearchProperties properties) {

        }

        @Override
        public void customize(RestClientBuilder builder) {
            builder.setDefaultHeaders(new BasicHeader[]{
                    new BasicHeader("Content-Type", "application/json"),
                    new BasicHeader("X-Elastic-Product", "charon")
            });
        }
    }

    @Resource
    private MappingContext<? extends ElasticsearchPersistentEntity<?>, ElasticsearchPersistentProperty> mappingContext;

    @Bean
    ElasticsearchConverter elasticsearchConverter() {
        GenericConversionService genericConversionService = new GenericConversionService();
        genericConversionService.addConverter(new StringToDateConverter());
        genericConversionService.addConverter(new DateToStringConverter());
        genericConversionService.addConverter(new IntegerToLongConverter());
        genericConversionService.addConverter(new StringToLongConverter());
        return new MappingElasticsearchConverter(mappingContext, genericConversionService);
    }

    @ReadingConverter
    static class StringToDateConverter implements Converter<String, Date> {

        @SneakyThrows
        @Override
        public Date convert(String source) {
            return DateUtils.parseDate(source, "yyyy-MM-dd HH:mm:ss");
        }
    }

    @ReadingConverter
    static class IntegerToLongConverter implements Converter<Integer, Long> {
        @Override
        public Long convert(Integer source) {
            return source.longValue();
        }
    }

    @ReadingConverter
    static class StringToLongConverter implements Converter<String, Long> {
        @Override
        public Long convert(String source) {
            return Long.valueOf(source);
        }
    }

    @WritingConverter
    static class DateToStringConverter implements Converter<Date, String> {

        @Override
        public String convert(Date source) {
            return DateFormatUtils.format(source, "yyyy-MM-dd HH:mm:ss");
        }
    }
}
