FROM alpine:3.14.3
LABEL maintainer="delverjw@gmail.com"

## 更换镜像
RUN sed -i "s/dl-cdn.alpinelinux.org/mirrors.aliyun.com/g"  /etc/apk/repositories

## 时区
RUN apk add tzdata && ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime  \
 && echo 'Asia/Shanghai' >/etc/timezone 
 
## 字体
RUN apk add fontconfig ttf-dejavu

## 常用包
RUN apk add git curl wget bash busybox-extras tini sysstat

WORKDIR /
