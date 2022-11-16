## 创建命名空间

```bash
kubectl create namespace mysql
```

## 部署 mysql-operator

```bash
helm install dev radondb/mysql-operator --namespace mysql
```

## 部署 RadonDB MySQL 集群
```bash
kubectl apply -f mysql_v1alpha1_mysqlcluster_mysql8.yaml
```