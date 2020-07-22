package io.github.harvies.charon.spring.boot.fastjson;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.fastjson")
@Getter
@Setter
public class FastJsonProperties {
    private String dateFormat;
}
