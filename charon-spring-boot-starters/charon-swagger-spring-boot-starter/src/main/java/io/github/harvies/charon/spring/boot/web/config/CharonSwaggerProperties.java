package io.github.harvies.charon.spring.boot.web.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import springfox.documentation.service.Contact;

/**
 * swagger配置
 */
@Getter
@Setter
@ConfigurationProperties(prefix = Constants.SWAGGER_PROPERTIES_PREFIX)
public class CharonSwaggerProperties {
    /**
     * 是否开启
     */
    private boolean enabled;
    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String description;
    /**
     * 服务条款
     */
    private String termsOfServiceUrl;
    /**
     * 联系人
     */
    private Contact contact;
    /**
     * 许可证
     */
    private String license;
    
    /**
     * 许可证地址
     */
    private String licenseUrl;

    /**
     * 版本
     */
    private String version;


}