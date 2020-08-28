docker run -d \
--name kodexplorer \
--restart=always \
-p 10800:80 \
-v /data/kodexplorer:/var/www/html \
dhso/kodexplorer:latest