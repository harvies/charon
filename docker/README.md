# docker脚本合集

## 
docker swarm init --advertise-addr 192.168.7.77

## 创建 overlay网络(swarm集群网络) 
docker network create -d overlay --scope swarm --subnet 10.77.0.0/24 --attachable dev_overlay

## 其他

- [docker-hadoop](https://github.com/big-data-europe/docker-hadoop) Hadoop环境本地一键部署
- [lanproxy-nat](https://github.com/harvies/lanproxy-nat) 内网穿透
- [docker-compose-mysql-master-slave](https://github.com/harvies/docker-compose-mysql-master-slave) Mysql主从架构
- [redis-cluster-docker-swarm](https://github.com/harvies/redis-cluster-docker-swarm) Redis集群

