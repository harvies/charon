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
RUN yum install -y wget telnet net-tools java-1.8.0-openjdk-devel.x86_64 wqy-microhei-fonts

## clean cache
RUN  yum clean all && rm -rf /tmp/* /var/cache/yum/*

# install arthas
RUN  wget -O /opt/arthas-boot.jar  https://arthas.aliyun.com/arthas-boot.jar

# Add SkyWalking-Agent
ARG SKYWALKING_VERSION="8.12.0"

RUN wget https://mirrors.aliyun.com/apache/skywalking/java-agent/${SKYWALKING_VERSION}/apache-skywalking-java-agent-${SKYWALKING_VERSION}.tgz \
    && tar -zxf apache-skywalking-java-agent-${SKYWALKING_VERSION}.tgz \
    && mv skywalking-agent /opt/skywalking-agent \
    &&  rm -rf apache-skywalking-java-agent-${SKYWALKING_VERSION}.tgz \
#    && mv /opt/skywalking-agent/optional-plugins/* /opt/skywalking-agent/plugins

RUN wget -O /opt/skywalking-agent/config/agent.config https://raw.githubusercontent.com/harvies/oss/master/configs/skywalking/agent.config

WORKDIR /
