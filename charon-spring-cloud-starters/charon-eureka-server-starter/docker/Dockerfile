FROM registry.cn-hangzhou.aliyuncs.com/harvies/jdk:latest
WORKDIR /source
RUN git clone git@github.com:harvies/charon.git
WORKDIR /source/charon/charon-eureka-server
RUN mvn clean package -DskipTests && cp target/charon-eureka-server-0.0.6.jar /app.jar
ENTRYPOINT ["/sbin/tini", "--","java","-XX:+UnlockExperimentalVMOptions","-XX:+UseCGroupMemoryLimitForHeap","-Djava.security.egd=file:/dev/./urandom","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005","-javaagent:/opt/skywalking/agent/skywalking-agent.jar","-jar","/app.jar"]
EXPOSE 8761



