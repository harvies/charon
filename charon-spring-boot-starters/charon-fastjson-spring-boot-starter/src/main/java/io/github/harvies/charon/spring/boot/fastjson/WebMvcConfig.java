package io.github.harvies.charon.spring.boot.fastjson;//package io.github.harvies.charon.spring.boot.gson;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Resource
    private FastJsonHttpMessageConverter fastJsonHttpMessageConverter;

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(0, fastJsonHttpMessageConverter);
    }
}