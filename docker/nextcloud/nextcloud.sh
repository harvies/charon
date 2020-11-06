docker run -d  --name nextcloud -p 808:80 --restart=always -v /data/nextcloud:/var/www/html -v /data2:/data2 harvies/nextcloud:latest
