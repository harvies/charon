package io.github.harvies.charon.elasticsearch.config;

import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.beans.factory.ObjectProvider;
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
import java.util.List;

@Configuration
public class ElasticSearchConfig {

    @Bean
    RestClient restClient(RestClientBuilder restClientBuilder, ObjectProvider<RestClientBuilderCustomizer> builderCustomizers) {

        //org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchRestClientConfigurations.RestClientBuilderConfiguration
        return restClientBuilder
                .setHttpClientConfigCallback(httpClientBuilder -> {
                    builderCustomizers.orderedStream().forEach((customizer) -> customizer.customize(httpClientBuilder));
                    httpClientBuilder.setDefaultHeaders(
                            List.of(new BasicHeader("Content-Type", "application/json"))
                    );
                    //兼容7.x服务端 https://stackoverflow.com/questions/71142680/co-elastic-clients-transport-transportexception-es-search-missing-x-elastic/74102828#74102828
                    httpClientBuilder.addInterceptorLast(
                            (HttpResponseInterceptor) (httpResponse, httpContext) -> httpResponse.addHeader("X-Elastic-Product", "Elasticsearch")
                    );
                    return httpClientBuilder;
                })
                .setRequestConfigCallback(requestConfigBuilder -> {
                    //建立连接
                    requestConfigBuilder.setConnectTimeout(5000);
                    //读取数据
                    requestConfigBuilder.setSocketTimeout(10000);
                    //获取连接
                    requestConfigBuilder.setConnectionRequestTimeout(3000);
                    return requestConfigBuilder;
                })
                .build();
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
