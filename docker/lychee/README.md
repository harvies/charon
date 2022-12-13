```

docker run -d \
--name=lychee \
-v /data/docker/lychee/conf:/conf \
-v /data/docker/lychee/uploads:/uploads \
-v /data/docker/lychee/sym:/sym \
-v /data:/data \
-e PUID=0 \
-e PGID=0 \
-e PHP_TZ=Asia/Shanghai \
-e DB_CONNECTION=mysql \
-e DB_HOST=mysql.dev.kikera.org \
-e DB_PORT=3306 \
-e DB_DATABASE=lychee \
-e DB_USERNAME=root \
-e DB_PASSWORD='password' \
-p 90:80 \
lycheeorg/lychee

```