## 介绍

整合redis-spring-boot(redis常用命令)和Redisson(分布式锁等)

如需使用Redisson，需引入
```xml
        <dependency>
            <groupId>org.redisson</groupId>
            <artifactId>redisson</artifactId>
        </dependency>
```

## 单机模式配置
```properties
charon.env=dev
charon.application.name=charon-redis
spring.redis.client-name=${charon.application.name}
spring.redis.host=
spring.redis.port=
spring.redis.password=
spring.redis.database=0
spring.redis.timeout=3s
```

## cluster模式配置
```properties
charon.env=dev
charon.application.name=charon-redis
spring.redis.client-name=${charon.application.name}
spring.redis.cluster.nodes=host1:port1,host2:port2,host3:port3
spring.redis.database=0
spring.redis.timeout=3s

```