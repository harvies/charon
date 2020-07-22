package io.github.harvies.charon.spring.boot.fastjson;

import com.alibaba.fastjson.JSONObject;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(JSONObject.class)
@EnableConfigurationProperties(FastJsonProperties.class)
public class FastJsonAutoConfiguration {
}
