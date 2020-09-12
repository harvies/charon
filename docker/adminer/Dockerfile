FROM adminer

USER root

RUN sed -i "s/dl-cdn.alpinelinux.org/mirrors.aliyun.com/g"  /etc/apk/repositories

RUN apk add autoconf build-base 
#RUN pecl install mongodb && echo 'extension=mongodb.so' > /usr/local/etc/php/conf.d/docker-php-ext-mongodb.ini
RUN apk del build-base

ADD info-scott.php /var/www/html/info-scott.php
ADD plugins-enabled /var/www/html/plugins-enabled