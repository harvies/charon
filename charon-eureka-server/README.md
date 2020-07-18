## eureka docker镜像

### FAQ
如果提示 ingress 不存在
```bash
docker network create --ingress --driver overlay ingress
```

### 使用方法
#### 部署eureka到swarm集群
```bash
docker network create --driver overlay dev_overlay

mvn package docker:build &&  docker stack rm eureka-cluster && docker stack  deploy -c  docker-compose.yml eureka-cluster

```
