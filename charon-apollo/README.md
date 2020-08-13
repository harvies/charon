1.2.2.2 跳过Apollo Meta Server服务发现
适用于apollo-client 0.11.0及以上版本

一般情况下都建议使用Apollo的Meta Server机制来实现Config Service的服务发现，从而可以实现Config Service的高可用。不过apollo-client也支持跳过Meta Server服务发现，主要用于以下场景：

Config Service部署在公有云上，注册到Meta Server的是内网地址，本地开发环境无法直接连接
如果通过公网 SLB 对外暴露 Config Service的话，记得要设置 IP 白名单，避免数据泄露
Config Service部署在docker环境中，注册到Meta Server的是docker内网地址，本地开发环境无法直接连接
Config Service部署在kubernetes中，希望使用kubernetes自带的服务发现能力（Service）
针对以上场景，可以通过直接指定Config Service地址的方式来跳过Meta Server服务发现，按照优先级从高到低分别为：

通过Java System Property apollo.configService
可以通过Java的System Property apollo.configService来指定
在Java程序启动脚本中，可以指定-Dapollo.configService=http://config-service-url:port
如果是运行jar文件，需要注意格式是java -Dapollo.configService=http://config-service-url:port -jar xxx.jar
也可以通过程序指定，如System.setProperty("apollo.configService", "http://config-service-url:port");
通过操作系统的System EnvironmentAPOLLO_CONFIGSERVICE
可以通过操作系统的System Environment APOLLO_CONFIGSERVICE来指定
注意key为全大写，且中间是_分隔
通过server.properties配置文件
可以在server.properties配置文件中指定apollo.configService=http://config-service-url:port
对于Mac/Linux，文件位置为/opt/settings/server.properties
对于Windows，文件位置为C:\opt\settings\server.properties