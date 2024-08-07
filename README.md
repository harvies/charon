## 项目介绍
 
自用Java开发工具包

代码正在逐步迁移走

## 项目要求

- JDK17

## 项目结构介绍

- [charon-core](./charon-core) 核心工具包
- [charon-dependencies](./charon-charon-dependencies) 依赖管理
- [charon-facade](./charon-facade)  rpc接口父模块
- [charon-eureka-server](./charon-eureka-server) eureka服务端
- [charon-socks-proxy-server](./charon-socks-proxy-server)  使用netty实现的socks5代理，摘自netty example
- [charon-spring-boot-admin-server](./charon-spring-boot-admin-server) spring-boot-admin服务端
- [charon-spring-boot-starters](./charon-spring-boot-starters)  charon-spring-boot-starter父模块
    - [charon-spring-boot-starter](./charon-spring-boot-starters/charon-spring-boot-starter)
      拓展spring-boot-starter,其他starter依赖此模块,新增从 属性 CHARON_CONFIG_PATH 及 ~/.charon/charon.properties,
      /opt/charon/charon.properties加载，属性指定配置文件名优先于家目录配置文件，优先于/opt目录配置文件
    - [charon-web-spring-boot-starter](./charon-spring-boot-starters/charon-web-spring-boot-starter)
      拓展spring-boot-starter,新增健康检查Controller及线程查看等运维工具
    - [charon-dubbo-spring-boot-starter](./charon-spring-boot-starters/charon-dubbo-spring-boot-starter) 集成dubbo
    - [charon-elasticjob-lite-spring-boot-starter](./charon-spring-boot-starters/charon-elasticjob-lite-spring-boot-starter)
      集成elasticjob-lite
    - [charon-elasticsearch-spring-boot-starter](./charon-spring-boot-starters/charon-elasticsearch-spring-boot-starter)
      集成elasticsearch
    - [charon-elk-spring-boot-starter](./charon-spring-boot-starters/charon-elk-spring-boot-starter) 输出logback日志到elk
    - [charon-mongo-spring-boot-starter](./charon-spring-boot-starters/charon-mongo-spring-boot-starter)
      拓展mongo-spring-boot-starter,新增多数据源功能
    - [charon-notify-spring-boot-starter](./charon-spring-boot-starters/charon-notify-spring-boot-starter) 一个推送(通知)
      starter，支持钉钉,Telegram等消息推送
    - [charon-oss-spring-boot-starter](./charon-spring-boot-starters/charon-oss-spring-boot-starter) 一个oss
      starter，支持阿里云OSS及GitHub
    - [charon-redis-spring-boot-starter](./charon-spring-boot-starters/charon-redis-spring-boot-starter)
      拓展redis-spring-boot-starter,集成redisson(更好的redis分布式锁实现)
    - [charon-swagger-spring-boot-starter](./charon-spring-boot-starters/charon-swagger-spring-boot-starter)
      集成swagger及bootstrap主题
    - [charon-shardingsphere-jdbc-spring-boot-starter](./charon-spring-boot-starters/charon-shardingsphere-jdbc-spring-boot-starter)
      集成shardingsphere-jdbc
    - [charon-spring-boot-admin-client-starter](./charon-spring-boot-starters/charon-spring-boot-admin-client-starter)
      集成spring-boot-amin-client
- [docker](./docker) 常用docker镜像

## 配置文件
拷贝配置文件charon.properties.example 到 ~/.charon/charon.properties

## 编译

```bash
./build.sh
```

## 部署

```bash

gpg --keyserver hkp://keyserver.ubuntu.com/ --send-keys 37D2FF9600A3CDD5482C4A1A368EBC0194F0DE96

gpg --keyserver hkp://keyserver.ubuntu.com/ --receive-keys 37D2FF9600A3CDD5482C4A1A368EBC0194F0DE96

export GPG_TTY=$(tty)

mvn clean deploy -DskipTests -U -Possrh
```

打开 https://oss.sonatype.org/

![](https://harvies-oss.oss-cn-hangzhou.aliyuncs.com/2021/07/08/20215408225400005-image.png)

点击 close

通过后点击Release

### gpg: signing failed: Inappropriate ioctl for device?

```bash
export GPG_TTY=$(tty)
```
