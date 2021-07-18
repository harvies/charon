package io.github.harvies.charon.feign;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;

import java.util.stream.Collectors;

@Configuration(proxyBeanMethods = false)
@AutoConfigureAfter(FeignAutoConfiguration.class)
public class CharonFeignAutoConfiguration {

    @Bean
    public CharonRequestInterceptor charonRequestInterceptor() {
        return new CharonRequestInterceptor();
    }

    @Bean
    public CustomWebFilter customWebFilter() {
        return new CustomWebFilter();
    }

    @Bean
    @ConditionalOnMissingBean
    public HttpMessageConverters messageConverters(ObjectProvider<HttpMessageConverter<?>> converters) {
        return new HttpMessageConverters(converters.orderedStream().collect(Collectors.toList()));
    }
}
