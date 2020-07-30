package io.github.harvies.charon.oss;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.net.Proxy;

/**
 * github配置
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "charon.oss.github")
public class GithubConfig {
    /**
     * 访问token
     */
    private String oauthAccessToken;
    /**
     * 用户名称
     */
    private String username;
    /**
     * 仓库名称
     */
    private String repositoryName;
    private String branch;
    /**
     * git提交者名称
     */
    private String committerName;
    /**
     * git提交者邮箱
     */
    private String committerEmail;
    /**
     * 自定义域名
     */
    private String customDomain;

    /**
     * 是否启用代理
     */
    private Boolean enableProxy = false;
    /**
     * 代理类型
     */
    private Proxy.Type proxyType;
    /**
     * 代理地址
     */
    private String proxyHost;
    /**
     * 代理端口
     */
    private int proxyPort;
}