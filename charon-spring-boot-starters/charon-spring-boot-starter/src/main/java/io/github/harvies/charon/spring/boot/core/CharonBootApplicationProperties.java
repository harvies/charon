package io.github.harvies.charon.spring.boot.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 应用配置
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "charon.application")
@ToString
public class CharonBootApplicationProperties {
    /**
     * 应用名称
     */
    private String name;


}