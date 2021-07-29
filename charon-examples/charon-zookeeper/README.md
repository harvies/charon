## 单机

运行 ZookeeperStandaloneBootstrap

## 集群

### 修改配置文件

conf/cluster/*

### 设置myid

```bash
mkdir -p /tmp/zookeeper/cluster/1 /tmp/zookeeper/cluster/2 /tmp/zookeeper/cluster/3
echo 1 > /tmp/zookeeper/cluster/1/myid
echo 2 > /tmp/zookeeper/cluster/2/myid
echo 3 > /tmp/zookeeper/cluster/3/myid
```

### 启动

运行 ZookeeperClusterNode1Bootstrap，ZookeeperClusterNode2Bootstrap，ZookeeperClusterNode3Bootstrap

### 命令查看

http://localhost:8002/commands

## 参考

https://www.cnblogs.com/-beyond/p/10993228.html
