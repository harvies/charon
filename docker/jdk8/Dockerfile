FROM openjdk:8-jdk-alpine
MAINTAINER cr0n6e@gmail.com

## 更换镜像
RUN sed -i "s/dl-cdn.alpinelinux.org/mirrors.aliyun.com/g"  /etc/apk/repositories
## 处理时区(alpine没有时区包)
RUN apk add tzdata curl bash busybox-extras maven openssh-client git tini fontconfig ttf-dejavu

## maven
RUN wget -P /usr/share/java/maven-3/conf  https://raw.githubusercontent.com/harvies/charon/master/.maven/settings.xml

## 时区
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone

# copy arthas
RUN wget  -P /opt https://arthas.gitee.io/arthas-boot.jar

WORKDIR /
