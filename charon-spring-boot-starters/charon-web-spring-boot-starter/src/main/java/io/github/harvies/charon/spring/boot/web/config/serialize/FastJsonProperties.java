package io.github.harvies.charon.spring.boot.web.config.serialize;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "charon.web.fastjson")
@Getter
@Setter
public class FastJsonProperties {
    private String dateFormat;
}
