package io.github.harvies.charon.oss;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * github配置
 */
@Getter
@Setter
@ConfigurationProperties(prefix = Constants.ALIYUN_PROPERTIES_PREFIX)
public class ALiYunOSSProperties {

}