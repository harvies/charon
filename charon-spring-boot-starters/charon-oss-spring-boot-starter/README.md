如果使用github，则引入
```xml
        <dependency>
            <groupId>org.kohsuke</groupId>
            <artifactId>github-api</artifactId>
        </dependency>
```
并配置
charon.oss.preferred=github  //默认aliyun
charon.oss.github.*

如果使用阿里云OSS，则引入
```xml
        <dependency>
            <groupId>com.aliyun.oss</groupId>
            <artifactId>aliyun-sdk-oss</artifactId>
        </dependency>
```
并配置
charon.oss.aliyun.*