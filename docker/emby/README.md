
## emby开心版

```bash
#!/bin/bash
docker run -d --name embyserver --volume /volume2/docker/programdata:/config --volume /volume2/Download:/mnt/Download --volume /volume3/video:/mnt/video --net=host --publish 8096:8096 --publish 8920:8920 --env UID=1000 --env GID=100 --env GIDLIST=100 zishuo/embyserver:latest
```

https://github.com/91270/Emby.MeiamSub