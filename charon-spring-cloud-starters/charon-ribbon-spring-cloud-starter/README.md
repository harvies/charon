header中包含 request_tag=harvies,会优先匹配metadata包含request_tag=harvies的服务，除非该服务下线，走正常负载均衡

![](https://harvies-oss.oss-cn-hangzhou.aliyuncs.com/2021/07/14/20215214155200054-image.png)

实现逻辑

io.github.harvies.charon.ribbon.CharonRule.requestTagDeal
io.github.harvies.charon.spring.boot.web.interceptor.CharonWebHandlerInterceptor.preHandle

```properties
spring.cloud.nacos.discovery.metadata.request_tag=harvies
```

- [x] 支持根据请求头标识优先路由到指定标识服务