## 介绍 

集成钉钉通知和server酱通知，可通过配置文件指定使用哪种通知


## 使用方法

### 钉钉通知

- 引入jar
```xml
        <dependency>
            <groupId>io.github.harvies.charon</groupId>
            <artifactId>charon-notify-spring-boot-starter</artifactId>
        </dependency>
```
- 新增如下配置
```properties
charon.notify.preferred=ding-talk
charon.oss.dingtalk.web-hook-url=https://oapi.dingtalk.com/robot/send?access_token=xxxxxxxxxx
```

- 配置举例
```properties
charon.env=dev
charon.application.name=charon-oss
charon.notify.preferred=ding-talk
charon.notify.dingtalk.web-hook-url = https://oapi.dingtalk.com/robot/send?access_token=6e0b2c81338adb4dc5088363c81549755c900f2769c1b074401d4ea16fdb2f5c

```

### server酱通知

- 引入jar
```xml
        <dependency>
            <groupId>io.github.harvies.charon</groupId>
            <artifactId>charon-notify-spring-boot-starter</artifactId>
        </dependency>
```
- 新增如下配置

```properties
charon.notify.preferred=server-sauce
charon.notify.server-sauce.web-hook-url = https://sc.ftqq.com/xxxxxxxxxxxxxx.send

```
> webhook获取地址 http://sc.ftqq.com/3.version

- 配置举例
```properties
charon.env=dev
charon.application.name=charon-oss
charon.notify.preferred=ding-talk
charon.notify.server-sauce.web-hook-url = https://sc.ftqq.com/xxxxxxxxxxxxxx.send


```