## eureka docker镜像

### FAQ
如果提示 ingress 不存在
```bash
docker network create --ingress --driver overlay ingress
```

### 使用方法
#### 部署eureka到swarm集群

> https://docs.docker.com/engine/reference/commandline/network_create/#network-ingress-mode
```bash
docker network create -d overlay --ingress --subnet=10.0.3.0/24 --attachable  dev_overlay
mvn package docker:build &&  docker stack rm eureka-cluster && docker stack  deploy -c  docker-compose.yml eureka-cluster

```

## 参考

https://cloud.spring.io/spring-cloud-netflix/multi/multi_spring-cloud-eureka-server.html