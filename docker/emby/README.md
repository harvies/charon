
## emby开心版

### 镜像
https://hub.docker.com/r/lovechen/embyserver

### 脚本

```bash
docker run \
--network=bridge \
-p '8096:8096' \
-p '8920:8920' \
-p '2900:1900/udp' \
-p '8359:7359/udp' \
-v /volume2/docker/emby:/config \
-v /volume3/video/:/data \
-e TZ="Asia/Shanghai" \
-e UID=0 \
-e GID=0 \
-e GIDLIST=0 \
--restart always \
--name emby \
-d lovechen/embyserver:4.7.6.0
```

old
```
docker run -d  --name embyserver --volume /volume2/docker/programdata:/config --volume /volume2/Download:/mnt/Download --volume /volume3/video:/mnt/video --net=bridge --publish 9096:8096 --publish 9920:8920 --env UID=0 --env GID=0 --env GIDLIST=0 zishuo/embyserver:latest
```

### 插件


#### JavScraper
https://github.com/JavScraper/Emby.Plugins.JavScraper

https://javscraper.harvies.workers.dev/

#### 字幕
https://github.com/91270/Emby.MeiamSub
