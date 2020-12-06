FROM registry.cn-hangzhou.aliyuncs.com/harvies/alpine:latest
LABEL maintainer="delverjw@gmail.com"

## jdk
ENV JAVA_HOME=/usr/lib/jvm/default-jvm
ENV PATH="${JAVA_HOME}/bin:${PATH}"
RUN apk add --no-cache openjdk11

# install arthas
RUN curl -L https://cdn.jsdelivr.net/gh/harvies/arthas-install@master/install_arthas.sh |sh

# Add SkyWalking-Agent
#ONBUILD
#RUN wget -O /opt/skywalking-agent.tar.gz https://harvies-cdn.oss-cn-hangzhou.aliyuncs.com/skywalking-agent.tar.gz \
# &&  cd /opt/ && tar -zxf skywalking-agent.tar.gz && rm -rf skywalking-agent.tar.gz
#
ARG SKYWALKING_VERSION="8.3.0"
RUN wget https://mirrors.aliyun.com/apache/skywalking/${SKYWALKING_VERSION}/apache-skywalking-apm-es7-${SKYWALKING_VERSION}.tar.gz \
    && tar -zxf apache-skywalking-apm-es7-${SKYWALKING_VERSION}.tar.gz \
    && mv apache-skywalking-apm-bin-es7/agent /opt/skywalking-agent \
    &&  rm -rf apache-skywalking-apm-bin-es7 apache-skywalking-apm-es7-${SKYWALKING_VERSION}.tar.gz \
    && wget -O /opt/skywalking-agent/config/agent.config https://raw.githubusercontent.com/harvies/oss/master/configs/skywalking/agent.config

WORKDIR /
