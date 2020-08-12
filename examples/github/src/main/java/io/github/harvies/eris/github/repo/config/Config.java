package io.github.harvies.eris.github.repo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author harvies
 */
@Configuration
@ConfigurationProperties(prefix = "github")
@Data
public class Config {
    private String oauthAccessToken;
}
