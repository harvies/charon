FROM alpine:3.12
MAINTAINER cr0n6e@gmail.com

## 更换镜像
RUN sed -i "s/dl-cdn.alpinelinux.org/mirrors.aliyun.com/g"  /etc/apk/repositories

## 处理时区(alpine没有时区包)
RUN apk add tzdata curl bash busybox-extras tini fontconfig ttf-dejavu && \
    rm -rf /var/cache/apk/*

## 时区
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone

WORKDIR /