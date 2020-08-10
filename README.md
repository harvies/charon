## 介绍

整合常用框架

* [charon-spring-boot-starter](./charon-spring-boot-starters/charon-spring-boot-starter)  对spring-boot-starter做的封装，新增配置.charon.properties加载(方便本地调试)
* [charon-web-spring-boot-starter](./charon-spring-boot-starters/charon-web-spring-boot-starter) 
* [charon-apollo-spring-boot-starter](./charon-spring-boot-starters/charon-apollo-spring-boot-starter) 
* [charon-dubbo-spring-boot-starter](./charon-spring-boot-starters/charon-dubbo-spring-boot-starter) 
* [charon-elasticjob-lite-spring-boot-starter](./charon-spring-boot-starters/charon-elasticjob-lite-spring-boot-starter) 
* [charon-elasticsearch-spring-boot-starter](./charon-spring-boot-starters/charon-elasticsearch-spring-boot-starter) 
* [charon-elk-spring-boot-starter](./charon-spring-boot-starters/charon-elk-spring-boot-starter) 
* [charon-fastjson-spring-boot-starter](./charon-spring-boot-starters/charon-fastjson-spring-boot-starter) 
* [charon-mongo-spring-boot-starter](./charon-spring-boot-starters/charon-mongo-spring-boot-starter) 
* [charon-notify-spring-boot-starter](./charon-spring-boot-starters/charon-notify-spring-boot-starter) 
* [charon-oss-spring-boot-starter](./charon-spring-boot-starters/charon-oss-spring-boot-starter) 
* [charon-redis-spring-boot-starter](./charon-spring-boot-starters/charon-redis-spring-boot-starter) 
* [charon-shardingsphere-jdbc-spring-boot-starter](./charon-spring-boot-starters/charon-shardingsphere-jdbc-spring-boot-starter) 
* [charon-spring-boot-admin-client-starter](./charon-spring-boot-starters/charon-spring-boot-admin-client-starter) 

## 编译

```bash
mvn clean install -DskipTests -U -s '.maven/settings.xml'
```

## 部署
```bash
mvn clean deploy -DskipTests -U -s '.maven/settings.xml'
```
![Maven Package](https://github.com/harvies/charon/workflows/Maven%20Package/badge.svg)
