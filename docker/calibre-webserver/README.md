docker rm -f calibre-web
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
calibredb add --library-path=/books -r /volume3/Share/Books


https://post.smzdm.com/p/adx0dnn/

https://www.jianshu.com/p/d0d04f6f246b

docker run -d  --name simple-boot-douban-api  -p 8085:8085 fugary/simple-boot-douban-api

http://192.168.7.76:8085/v2/book/search?q=%E7%BA%A2%E6%A5%BC%E6%A2%A6
