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
    "id": "charon-feign",
    "uri": "lb://charon-feign/",
    "order": 0,
    "predicates": [
      {
        "args": {
          "pattern": "/charon-feign/**"
        },
        "name": "Path"
      }
    ],
    "filters": [
      {
        "name": "StripPrefix",
        "args": [
          {
            "parts": "1"
          }
        ]
      }
    ]
  },
  {
    "filters": [],
    "id": "blazars-web",
    "order": 0,
    "predicates": [
      {
        "args": {
          "pattern": "/blazars-web/**"
        },
        "name": "Path"
      }
    ],
    "uri": "https://api.kikera.top:4433/"
  }
]
```

> StripPrefix 去除前缀过滤器
> 访问 http://localhost:8080/charon-feign/hello 时默认会转发给 lb://charon-feign/charon-feign/hello
> 需要转发给 lb://charon-feign/hello 则需要配置 StripPrefix 过滤器，parts=1是去除上一级前缀

## 参考

[Building a Gateway](https://spring.io/guides/gs/gateway/)

[Spring Cloud Gateway](https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html)

[基于Nacos实现Spring Cloud Gateway实现动态路由](https://blog.csdn.net/zhangchangbin123/article/details/89310353)