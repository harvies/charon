## 创建证书
```bash
kubectl create secret tls blog-tls --key blog.harvies.top.key --cert blog.harvies.top_bundle.crt
```

## 暴露服务
```bash
apiVersion: traefik.containo.us/v1alpha1
kind: IngressRoute
metadata:
  name: www-blog-http
spec:
  entryPoints:
  - web
  routes:
  - match: Host(`blog.harvies.top`)
    kind: Rule
    services:
      - name: blog
        port: 8090 
    middlewares:
    - name: redirect-https-middleware
---
apiVersion: traefik.containo.us/v1alpha1
kind: IngressRoute
metadata:
  name: www-blog-https
spec:
  entryPoints:
  - websecure
  routes:
  - match: Host(`blog.harvies.top`)
    kind: Rule
    services:
      - name: blog
        port: 8090
  tls:
    secretName: blog-tls
---
apiVersion: traefik.containo.us/v1alpha1
kind: Middleware
metadata:
  name: redirect-https-middleware
spec:
  redirectScheme:
    scheme: https
```


## 参考
https://blog.harvies.top/archives/zai-kubernetes-zhong-bu-shu-halo-bo-ke