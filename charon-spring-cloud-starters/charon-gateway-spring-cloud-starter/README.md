## 查看routes

```shell
http://localhost:8080/actuator/gateway/routes
```

## 动态路由

dataId : routing-rule

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
    "id": "charon-ribbon",
    "uri": "lb://charon-ribbon/",
    "order": 0,
    "predicates": [
      {
        "args": {
          "pattern": "/charon-ribbon/**"
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
    "filters": [
      {
        "name": "StripPrefix",
        "args": [
          {
            "parts": "1"
          }
        ]
      }
    ],
    "uri": "https://api.kikera.top:4433/"
  }
]
```

> StripPrefix 去除前缀过滤器
> 访问 http://localhost:8080/charon-feign/echo/hello 时默认会转发给 lb://charon-feign/charon-feign/echo/hello
> 需要转发给 lb://charon-feign/echo/hello 则需要配置 StripPrefix 过滤器，parts=1是去除上一级前缀


![](https://harvies-oss.oss-cn-hangzhou.aliyuncs.com/2021/07/18/20210418180400047-image.png)

![lALPD3lGuwqikRjNAaXNBHU_1141_421.png](https://i.loli.net/2021/07/29/VNtjHhflwdFXQxs.png)

![](https://harvies-oss.oss-cn-hangzhou.aliyuncs.com/2021/07/19/20211619001600016-image.png)

根据header头指定group优先路由

请求 [http://localhost:8080/charon-feign/echo/world](http://localhost:8080/charon-feign/echo/world)
请求路径 > charon-gateway > charon-feign > charon-ribbon > charon-sleuth

io.github.harvies.charon.spring.boot.webflux.CustomWebFilter 将header中的标识存储到ThreadLocal,feign调用下一个服务时，从ThreadLocal中把标识取出通过io.github.harvies.charon.feign.CharonRequestInterceptor写入到下一个服务，

## 参考

[Building a Gateway](https://spring.io/guides/gs/gateway/)

[Spring Cloud Gateway](https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html)

[基于Nacos实现Spring Cloud Gateway实现动态路由](https://blog.csdn.net/zhangchangbin123/article/details/89310353)