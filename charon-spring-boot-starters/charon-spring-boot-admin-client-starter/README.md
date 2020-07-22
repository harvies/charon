## 常见问题

- Controller之前返回的是json，引入该包后变成xml了？

原因是eureka-client引入了xml序列化包,优先级较高，而请求header没有指定Accept
解决方法:
- Controller RequestMapping加入produces属性,例如 @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
- 将json HttpMessageConverter 加入list头部，优先使用json序列化