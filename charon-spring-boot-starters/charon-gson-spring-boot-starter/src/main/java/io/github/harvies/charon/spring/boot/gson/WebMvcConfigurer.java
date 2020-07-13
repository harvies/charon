//package io.github.harvies.charon.spring.boot.gson;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.converter.json.GsonHttpMessageConverter;
//
//import java.util.List;
//
//@Configuration
//public class WebMvcConfigurer implements org.springframework.web.servlet.config.annotation.WebMvcConfigurer {
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        GsonHttpMessageConverter converter = new GsonHttpMessageConverter();
//        //自定义配置...
//        //FastJsonConfig config = new FastJsonConfig();
//        //config.set ...
//        //converter.setFastJsonConfig(config);
//        converters.add(0, converter);
//    }
//}