package io.github.harvies.charon.oss;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({ALiYunOSSProperties.class, GithubProperties.class})
public class OSSAutoConfiguration {

    static final String PREFERRED_NOTIFY_PROPERTY = Constants.OSS_PROPERTIES_PREFIX + ".preferred";

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(name = PREFERRED_NOTIFY_PROPERTY, havingValue = Constants.ALIYUN, matchIfMissing = true)
    OSSProvider aliyun(ALiYunOSSProperties aLiYunOSSProperties) {
        return new ALiYunOSSProvider(aLiYunOSSProperties);
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(name = PREFERRED_NOTIFY_PROPERTY, havingValue = Constants.GITHUB)
    OSSProvider github(GithubProperties githubProperties) {
        return new GithubOSSProvider(githubProperties);
    }
}
