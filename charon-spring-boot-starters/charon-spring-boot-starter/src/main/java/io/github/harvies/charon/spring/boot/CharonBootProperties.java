package io.github.harvies.charon.spring.boot;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 配置
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "charon")
public class CharonBootProperties {
    /**
     * 环境名称
     */
    private String env;

}