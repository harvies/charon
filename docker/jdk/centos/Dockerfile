FROM centos:7

MAINTAINER cr0n6e@gmail.com

## 中文支持
ENV LANG=en_US.UTF-8 \
    LANGUAGE=en_US:en \
    LC_ALL=en_US.UTF-8
    
RUN yum reinstall -y glibc-common && localedef -c -f UTF-8 -i en_US en_US.utf8

## 时区
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

## jdk
RUN yum install -y wget telnet net-tools java-11-openjdk-devel wqy-microhei-fonts

## clean cache
RUN  yum clean all && rm -rf /tmp/* /var/cache/yum/*

# install arthas
RUN curl -L https://cdn.jsdelivr.net/gh/harvies/arthas-install@master/install_arthas.sh |sh
## https://harvies-cdn.oss-cn-hangzhou.aliyuncs.com/charon-socks-proxy-server-0.0.5-SNAPSHOT-jar-with-dependencies.jar
 
ARG SKYWALKING_VERSION="8.1.0"
 
## Add SkyWalking-Agent	
WORKDIR /opt/skywalking/src	
RUN wget https://mirrors.aliyun.com/apache/skywalking/${SKYWALKING_VERSION}/apache-skywalking-apm-es7-${SKYWALKING_VERSION}.tar.gz \	
&& tar -zxf apache-skywalking-apm-es7-${SKYWALKING_VERSION}.tar.gz && mv apache-skywalking-apm-bin-es7/agent .. \
&& rm -rf /opt/skywalking/src
RUN wget -O /opt/skywalking/agent/config/agent.config https://raw.githubusercontent.com/harvies/oss/master/configs/skywalking/agent.config 

WORKDIR /