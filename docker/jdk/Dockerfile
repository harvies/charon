FROM registry.cn-hangzhou.aliyuncs.com/harvies/alpine:latest
MAINTAINER cr0n6e@gmail.com

## jdk
ENV JAVA_HOME=/usr/lib/jvm/default-jvm
ENV PATH="${JAVA_HOME}/bin:${PATH}"
RUN apk add --no-cache openjdk11

# install arthas
RUN curl -L https://cdn.jsdelivr.net/gh/harvies/arthas-install@master/install_arthas.sh |sh
 
# Add SkyWalking-Agent	

ARG SKYWALKING_VERSION="8.1.0"

# skywalking
RUN wget https://mirrors.aliyun.com/apache/skywalking/${SKYWALKING_VERSION}/apache-skywalking-apm-es7-${SKYWALKING_VERSION}.tar.gz \ 
    && tar -zxf apache-skywalking-apm-es7-${SKYWALKING_VERSION}.tar.gz && mkdir /opt/skywalking \ 
    && mv apache-skywalking-apm-bin-es7/agent/ /opt/skywalking/ \ 
    &&  rm -rf apache-skywalking-apm-bin-es7 apache-skywalking-apm-es7-${SKYWALKING_VERSION}.tar.gz \
    && wget -O /opt/skywalking/agent/config/agent.config https://code.aliyun.com/harvies/oss/raw/master/configs/skywalking/agent.config \

# skywalking third plugin
ONBUILD RUN wget -O /opt/skywalking/agent/plugins/apm-mongodb-4.x-plugin-8.2.0-SNAPSHOT.jar https://harvies-cdn.oss-cn-hangzhou.aliyuncs.com/apm-mongodb-4.x-plugin-8.2.0-SNAPSHOT.jar    
    
WORKDIR /