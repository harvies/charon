FROM openjdk:8-jdk-alpine
MAINTAINER cr0n6e@gmail.com

## 更换镜像
RUN sed -i "s/dl-cdn.alpinelinux.org/mirrors.aliyun.com/g"  /etc/apk/repositories
## 处理时区(alpine没有时区包)
RUN apk add tzdata curl bash busybox-extras maven openssh-client git tini fontconfig ttf-dejavu

## maven
#COPY settings.xml /usr/share/java/maven-3/conf
### ssh
#RUN mkdir /root/.ssh
#COPY id_rsa /root/.ssh/
#RUN chmod 600 /root/.ssh/id_rsa
#
## Create known_hosts
#RUN touch /root/.ssh/known_hosts
#RUN chmod 600 /root/.ssh/known_hosts
## Add known_hosts
#RUN ssh-keyscan code.aliyun.com >> /root/.ssh/known_hosts

## 时区
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone

# copy arthas
RUN wget  -P /opt https://arthas.gitee.io/arthas-boot.jar

WORKDIR /
