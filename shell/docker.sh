## 删除无用镜像

```
 docker images|awk {'print $1,$3}'|grep none|awk {'print $2'}|xargs docker rmi --force
 
```