package io.github.harvies.charon.oss;

public class Constants {
    /**
     * oss前缀
     */
    public static final String OSS_PROPERTIES_PREFIX = "charon.oss";

    /**
     * 阿里云
     */
    public static final String ALIYUN = "aliyun";

    /**
     * GitHub
     */
    public static final String GITHUB = "github";
    /**
     * 阿里云配置前缀
     */
    public static final String ALIYUN_PROPERTIES_PREFIX = OSS_PROPERTIES_PREFIX + "." + ALIYUN;
    /**
     * GitHub配置前缀
     */
    public static final String GITHUB_PROPERTIES_PREFIX = OSS_PROPERTIES_PREFIX + "." + GITHUB;
}
