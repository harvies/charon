package io.github.harvies.charon.spring.boot.web.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import javax.annotation.Resource;

@Configuration
@EnableSwagger2WebMvc
@ConditionalOnProperty(name = Constants.SWAGGER_PROPERTIES_PREFIX + ".enabled", havingValue = "true")
public class Swagger2Config {

    @Resource
    private CharonSwaggerProperties properties;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //防止乱码
                .title(properties.getTitle())
                .description(properties.getDescription())
                .version(properties.getVersion())
                .contact(properties.getContact())
                .license(properties.getLicense())
                .licenseUrl(properties.getLicenseUrl())
                .termsOfServiceUrl(properties.getTermsOfServiceUrl())
                .build();
    }
}
