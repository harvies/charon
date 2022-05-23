```
docker run \
--network=bridge \
-p '19035:19035' \
-v /volume2/docker/chinesesubfinder/config:/config \
-v /volume2/docker/chinesesubfinder/cache:/app/cache \
-v /volume3/video/:/media \
-e TZ="Asia/Shanghai" \
-e PUID=1026 \
-e PGID=100 \
--restart always \
--name chinesesubfinder \
-d allanpk716/chinesesubfinder:v0.27.3
```

```dockerfile
version: "3"
services:
  chinesesubfinder:
    image: allanpk716/chinesesubfinder:latest
    volumes:
      - /volume1/docker/chinesesubfinder/config:/config
      - /volume1/docker/chinesesubfinder/cache:/app/cache
      - /volume1/Video:/media
    environment:
      - PUID=1026
      - PGID=100
      - TZ=Asia/Shanghai
    ports:
      - 19035:19035
    restart: unless-stopped
```

https://hub.docker.com/r/allanpk716/chinesesubfinder
