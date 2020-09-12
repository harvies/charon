FROM alpine:3.12
MAINTAINER cr0n6e@gmail.com

ARG SKYWALKING_VERSION="8.1.0"

## 更换镜像
RUN sed -i "s/dl-cdn.alpinelinux.org/mirrors.aliyun.com/g"  /etc/apk/repositories
## 处理时区(alpine没有时区包)
RUN apk add openjdk11 tzdata curl bash busybox-extras tini fontconfig ttf-dejavu

## 时区
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone

# install arthas
RUN curl -L https://cdn.jsdelivr.net/gh/harvies/arthas-install@master/install_arthas.sh |sh
 
# Add SkyWalking-Agent	
WORKDIR /opt/skywalking/src	
RUN wget https://mirrors.aliyun.com/apache/skywalking/${SKYWALKING_VERSION}/apache-skywalking-apm-es7-${SKYWALKING_VERSION}.tar.gz \	
&& tar -zxf apache-skywalking-apm-es7-${SKYWALKING_VERSION}.tar.gz && mv apache-skywalking-apm-bin-es7/agent .. && rm -rf /opt/skywalking/src

RUN wget -O /opt/skywalking/agent/config/agent.config https://raw.githubusercontent.com/harvies/oss/master/configs/skywalking/agent.config 

WORKDIR /