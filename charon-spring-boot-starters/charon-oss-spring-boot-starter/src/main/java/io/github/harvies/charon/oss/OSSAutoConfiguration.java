package io.github.harvies.charon.oss;

import org.kohsuke.github.GHCommit;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({GithubConfig.class})
public class OSSAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass(GHCommit.class)
    OSSService github(GithubConfig githubConfig) {
        return new GithubOSSService(githubConfig);
    }
}
