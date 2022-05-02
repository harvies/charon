
## emby开心版

### 镜像
https://hub.docker.com/r/lovechen/embyserver

### 脚本
```bash
docker run \
--network=bridge \
-p '9096:8096' \
-p '9920:8920' \
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
-d lovechen/embyserver:4.7.0.35
```

### 插件


#### JavScraper
https://github.com/JavScraper/Emby.Plugins.JavScraper

https://javscraper.harvies.workers.dev/

#### 字幕
https://github.com/91270/Emby.MeiamSub
