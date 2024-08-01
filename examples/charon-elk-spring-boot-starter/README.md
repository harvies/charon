## 介绍 

输出logback日志到logstash,通过kibana查询

## 使用

- 配置logstash

```
vi /etc/logstash/conf.d/logstash.conf 

# Sample Logstash configuration for creating a simple
# Beats -> Logstash -> Elasticsearch pipeline.

input {
        tcp {
        port => 10514
        codec => "json"
    }
}

output {
  elasticsearch {
    hosts => ["http://localhost:9200"]
    index => "%{[appName]}"
    #user => "elastic"
    #password => "changeme"
  }
}
```

- 引包

```xml
        <dependency>
            <groupId>io.github.harvies.charon</groupId>
            <artifactId>charon-elk-spring-boot-starter</artifactId>
        </dependency>
```

- 配置 logback-spring.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration scan="true">
    <contextName>application</contextName>
    <timestamp key="TIMESTAMP" datePattern="yyyy-MM-dd"/>
    <property name="LOGPATH" value="log"/>
    <springProperty scope="context" name="appName" source="charon.application.name" defaultValue="app"/>
    <springProperty scope="context" name="logstashHost" source="charon.logstash.host" defaultValue="localhost"/>
    <springProperty scope="context" name="logstashPort" source="charon.logstash.port" defaultValue="10514"/>
    <!-- 输出到控制台 -->
    <appender name="stdout" class="ch.qos.logback.core.ConsoleAppender">
        <layout class="ch.qos.logback.classic.PatternLayout">
            <pattern>
                %d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{40} - %msg%n
            </pattern>
        </layout>
    </appender>

    <!-- 输出到文件 -->
    <appender name="fileLog"
              class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>${LOGPATH}${file.separator}${TIMESTAMP}.log</file>
        <append>true</append>
        <encoder>
            <pattern>
                %d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{40} - %msg%n
            </pattern>
        </encoder>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>${LOGPATH}${file.separator}all${file.separator}%d{yyyy-MM-dd}.log</fileNamePattern>
            <maxHistory>30</maxHistory>
        </rollingPolicy>
        <triggeringPolicy class="ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy">
            <MaxFileSize>10MB</MaxFileSize>
        </triggeringPolicy>
    </appender>

    <appender name="logstash" class="net.logstash.logback.appender.LogstashTcpSocketAppender">
        <param name="Encoding" value="UTF-8"/>
        <remoteHost>${logstashHost}</remoteHost>
        <port>${logstashPort}</port>
        <!-- <filter class="com.program.interceptor.ELKFilter"/>-->
        <!-- encoder is required -->
        <encoder charset="UTF-8" class="net.logstash.logback.encoder.LogstashEncoder">
            <customFields>{"appName":"${appName}"}</customFields>
        </encoder>
    </appender>


    <root level="INFO">
        <appender-ref ref="fileLog"/>
        <appender-ref ref="stdout"/>
        <appender-ref ref="logstash"/>
    </root>
</configuration>
```

- 效果图

![](https://harvies-cdn.oss-cn-hangzhou.aliyuncs.com/2020/08/10/20203410113400031-image.png)

![](https://harvies-cdn.oss-cn-hangzhou.aliyuncs.com/2020/08/10/20203510113500013-image.png)

![](https://harvies-cdn.oss-cn-hangzhou.aliyuncs.com/2020/08/10/20203610113600045-image.png)
