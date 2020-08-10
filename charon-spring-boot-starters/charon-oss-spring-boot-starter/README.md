## 介绍

集成阿里云oss文件存储和GitHub仓库存储(支持设置cdn,默认jsdelivr cdn),默认阿里云OSS

## 使用方法

### 阿里云OSS

- 引入jar
```xml
        <dependency>
            <groupId>io.github.harvies.charon</groupId>
            <artifactId>charon-oss-spring-boot-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>com.aliyun.oss</groupId>
            <artifactId>aliyun-sdk-oss</artifactId>
        </dependency>
```
- 新增如下配置
```properties
charon.oss.preferred=aliyun
charon.oss.aliyun.*
```

- 配置举例
```properties
charon.env=dev
charon.application.name=charon-oss
charon.oss.preferred=aliyun
charon.oss.aliyun.access-key-id = xxxxxxx
charon.oss.aliyun.bucket-name = xxx
charon.oss.aliyun.access-key-secret = xxxxxx
charon.oss.aliyun.endpoint = oss-cn-hangzhou.aliyuncs.com

```

### GitHub仓库存储

- 引入jar 

```xml
        <dependency>
            <groupId>io.github.harvies.charon</groupId>
            <artifactId>charon-oss-spring-boot-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>org.kohsuke</groupId>
            <artifactId>github-api</artifactId>
        </dependency>
```
- 新增如下配置

```properties
charon.oss.preferred=github
charon.oss.github.*

```
- 配置举例
```properties
charon.env=dev
charon.application.name=charon-oss
charon.oss.preferred=github
charon.oss.github.username = harvies
charon.oss.github.oauthAccessToken = xxxxxxxxxxxxxxxxxxx
charon.oss.github.repositoryName = oss
charon.oss.github.branch = master
charon.oss.github.committerName = harvies
charon.oss.github.committerEmail = cr0n6e@gmail.com
charon.oss.github.customDomain = https://cdn.jsdelivr.net/gh/harvies/oss@master
charon.oss.github.enableProxy = false
charon.oss.github.proxyType = SOCKS
charon.oss.github.proxyHost = localhost
charon.oss.github.proxyPort = 1080

```