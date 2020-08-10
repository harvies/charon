## 介绍

整合swagger及bootstrap主题

## 用法

- 引入jar

```xml
<dependency>
            <groupId>io.github.harvies.charon</groupId>
            <artifactId>charon-swagger-spring-boot-starter</artifactId>
        </dependency>
```

- 配置
```properties
charon.web.swagger.enabled=true
charon.web.swagger.*=
```
- 配置例子 
```properties
charon.application.name=charon-swagger
charon.env=dev
charon.web.swagger.enabled=true
charon.web.swagger.version=1.0.0
## properties文件，需unicode编码中文
charon.web.swagger.title=\u6D4B\u8BD5
charon.web.swagger.description=\u6D4B\u8BD5
```

- 访问地址

http://localhost:8080/doc.html

> 替换地址及端口

- 效果图

![](https://harvies-cdn.oss-cn-hangzhou.aliyuncs.com/2020/08/10/20200910160900013-image.png)
