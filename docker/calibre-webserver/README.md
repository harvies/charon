docker rm -f calibre-web
rm -rf /volume3/docker/apps/calibre-web/config
rm -rf /volume3/books/calibre
mkdir -p /volume3/books/calibre
mkdir -p /volume3/docker/apps/calibre-web/config

docker run -d --name=calibre-web --restart=always \
-v /volume3/books/calibre:/books \
-v /volume3/docker/apps/calibre-web/config:/calibre-web/config \
-v /volume3:/volume3 \
-e USE_CONFIG_DIR=true \
-e SET_CONTAINER_TIMEZONE=true \
-e CONTAINER_TIMEZONE=Asia/Shanghai \
-e PGID=65539 -e PUID=1029 \
-p 8083:8083 \
technosoft2000/calibre-web

cd /calibre-web/app/cps/metadata_provider
wget https://raw.githubusercontent.com/fugary/calibre-web-douban-api/main/src/NewDouban.py

## 扫描

calibredb add --library-path=/books -r /volume3/Share/Books/Redis

admin admin123

管理权限-编辑基本配置-功能配置

勾选 Convert non-English characters in title and author while saving to disk

https://tieba.baidu.com/p/2423609942?red_tag=0581360257

https://post.smzdm.com/p/adx0dnn/

https://www.jianshu.com/p/d0d04f6f246b

docker run -d  --name simple-boot-douban-api  -p 8085:8085 fugary/simple-boot-douban-api

http://192.168.7.76:8085/v2/book/search?q=%E7%BA%A2%E6%A5%BC%E6%A2%A6
