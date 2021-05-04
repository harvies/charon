FROM centos:7

LABEL maintainer="delverjw@gmail.com"

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

# Add SkyWalking-Agent
#ONBUILD
#RUN wget -O /opt/skywalking-agent.tar.gz https://harvies-cdn.oss-cn-hangzhou.aliyuncs.com/skywalking-agent.tar.gz \
#&&  cd /opt/ && tar -zxf skywalking-agent.tar.gz && rm -rf skywalking-agent.tar.gz
#
ARG SKYWALKING_VERSION="8.5.0"
RUN wget https://mirrors.aliyun.com/apache/skywalking/${SKYWALKING_VERSION}/apache-skywalking-apm-es7-${SKYWALKING_VERSION}.tar.gz \
    && tar -zxf apache-skywalking-apm-es7-${SKYWALKING_VERSION}.tar.gz \
    && mv apache-skywalking-apm-bin-es7/agent /opt/skywalking-agent \
    &&  rm -rf apache-skywalking-apm-bin-es7 apache-skywalking-apm-es7-${SKYWALKING_VERSION}.tar.gz

RUN wget -O /opt/skywalking-agent/config/agent.config https://cdn.jsdelivr.net/gh/harvies/oss@master/2021/05/04/20211004111000003-agent.config

WORKDIR /