FROM registry.cn-hangzhou.aliyuncs.com/harvies/alpine:latest
MAINTAINER cr0n6e@gmail.com

ARG SKYWALKING_VERSION="8.1.0"

RUN apk add openjdk11

# install arthas
RUN curl -L https://cdn.jsdelivr.net/gh/harvies/arthas-install@master/install_arthas.sh |sh
 
# Add SkyWalking-Agent	
RUN wget https://mirrors.aliyun.com/apache/skywalking/${SKYWALKING_VERSION}/apache-skywalking-apm-es7-${SKYWALKING_VERSION}.tar.gz && \ 
    tar -zxf apache-skywalking-apm-es7-${SKYWALKING_VERSION}.tar.gz && mkdir /opt/skywalking &&\ 
    mv apache-skywalking-apm-bin-es7/agent/ /opt/skywalking/ &&\ 
    rm -rf apache-skywalking-apm-bin-es7 apache-skywalking-apm-es7-${SKYWALKING_VERSION}.tar.gz

RUN wget -O /opt/skywalking/agent/config/agent.config https://raw.githubusercontent.com/harvies/oss/master/configs/skywalking/agent.config 

WORKDIR /