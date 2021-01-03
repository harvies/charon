## @Value动态刷新

在@Value所在类加上@RefreshScope注解，通过配置中心修改配置后，BeanRefreshScope缓存会被情况，再获取@Value修饰的对象时，会重新实例化@RefreshScope修饰的类
