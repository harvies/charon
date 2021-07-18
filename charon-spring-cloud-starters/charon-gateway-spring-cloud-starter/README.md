## 查看routes

```shell
http://localhost:8080/actuator/gateway/routes
```

## 动态路由

dataId ${spring.application.name}-route-rule-${spring.profiles.active}

![](https://harvies-oss.oss-cn-hangzhou.aliyuncs.com/2021/07/18/20214018124000006-image.png)

路由配置example

```json
[
  {
    "filters": [],
    "id": "kikera",
    "order": 0,
    "predicates": [
      {
        "args": {
          "pattern": "/**"
        },
        "name": "Path"
      }
    ],
    "uri": "https://api.kikera.top:4433/"
  }
]
```

## 参考

[Building a Gateway](https://spring.io/guides/gs/gateway/)

[Spring Cloud Gateway](https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html)
