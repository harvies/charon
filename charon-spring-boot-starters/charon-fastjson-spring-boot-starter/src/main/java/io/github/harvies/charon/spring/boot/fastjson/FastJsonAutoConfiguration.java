package io.github.harvies.charon.spring.boot.fastjson;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(JSONObject.class)
@EnableConfigurationProperties(FastJsonProperties.class)
public class FastJsonAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public FastJsonConfig fastJsonConfig() {
        FastJsonConfig config = new FastJsonConfig();
        config.setDateFormat("yyyy-MM-dd HH:mm:ss");
        return config;
    }

    @Bean
    @ConditionalOnMissingBean
    public FastJsonHttpMessageConverter fastJsonHttpMessageConverter(FastJsonConfig config) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        converter.setFastJsonConfig(config);
        return converter;
    }
}
