FROM alpine:3.12

LABEL maintainer="Milan Sulc <sulcmil@gmail.com>"

ENV ADMINER_VERSION=4.7.7
ENV MEMORY=256M
ENV UPLOAD=2048M

RUN sed -i "s/dl-cdn.alpinelinux.org/mirrors.aliyun.com/g"  /etc/apk/repositories

RUN echo '@testing http://mirrors.aliyun.com/alpine/edge/testing' >> /etc/apk/repositories && \
    echo '@community http://mirrors.aliyun.com/alpine/edge/community' >> /etc/apk/repositories && \
    apk update && \
    apk upgrade && \
    apk add \
        wget \
        ca-certificates \
        php7@testing \
        php7-session@testing \
        php7-mysqli@community \
        php7-pgsql@testing \
        php7-json@testing \
        php7-pecl-mongodb@testing \
        dumb-init && \
        wget https://github.com/vrana/adminer/releases/download/v$ADMINER_VERSION/adminer-$ADMINER_VERSION.php -O /srv/adminer.php && \
    apk del wget ca-certificates && \
    rm -rf /var/cache/apk/*

ADD index.php /srv/index.php
ADD designs /srv/designs
ADD plugins /srv/plugins
ADD plugins-enabled /srv/plugins-enabled
ADD info-scott.php /srv/info-scott.php

WORKDIR /srv
EXPOSE 80

ENTRYPOINT ["dumb-init", "--"]

CMD /usr/bin/php \
    -d memory_limit=$MEMORY \
    -d upload_max_filesize=$UPLOAD \
    -d post_max_size=$UPLOAD \
    -S 0.0.0.0:80