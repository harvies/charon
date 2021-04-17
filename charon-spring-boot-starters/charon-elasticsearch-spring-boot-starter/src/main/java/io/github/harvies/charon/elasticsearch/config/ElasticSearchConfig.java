package io.github.harvies.charon.elasticsearch.config;

import lombok.SneakyThrows;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
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

import javax.annotation.Resource;
import java.util.Date;

@Configuration
public class ElasticSearchConfig {

    @Resource
    private MappingContext<? extends ElasticsearchPersistentEntity<?>, ElasticsearchPersistentProperty> mappingContext;

    @Bean
    ElasticsearchConverter elasticsearchConverter() {
        GenericConversionService genericConversionService = new GenericConversionService();
        genericConversionService.addConverter(new StringToDateConverter());
        genericConversionService.addConverter(new DateToStringConverter());
        genericConversionService.addConverter(new IntegerToLongConverter());
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
            if (source == null) {
                return null;
            }
            return source.longValue();
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
