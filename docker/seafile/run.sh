#!/bin/bash
docker run -d  --name seafile -p 809:80 -p 810:810 -e DB_HOST='192.168.7.77'  -e DB_ROOT_PASSWD='8mx3@kHn!CYRF2G' -e TIME_ZONE='Asia/Shanghai' -e SEAFILE_ADMIN_EMAIL='me@example.com' -e SEAFILE_ADMIN_PASSWORD=asecret -e SEAFILE_SERVER_LETSENCRYPT=false -e SEAFILE_SERVER_HOSTNAME='seafile.kikera.org' --restart=always -v /data2/seafile-data:/shared  registry.cn-hangzhou.aliyuncs.com/harvies/seafile-pro-mc:8.0.8
