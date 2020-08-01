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
    /**
     * 访问OSS的域名
     */
    private String endpoint;
    /**
     * accessKeyId
     */
    private String accessKeyId;
    /**
     * accessKeySecret
     */
    private String accessKeySecret;
    /**
     * 用来管理所存储Object的存储空间
     */
    private String bucketName;

}