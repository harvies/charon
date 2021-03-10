FROM harvies/jdk:latest
WORKDIR /source
RUN git clone https://github.com/harvies/charon.git
WORKDIR /source/charon/charon-socks-proxy-server
RUN mvn clean package -DskipTests && cp target/charon-socks-proxy-server.jar /app.jar
ENTRYPOINT ["/sbin/tini", "--","java","-XX:+UnlockExperimentalVMOptions","-XX:+UseCGroupMemoryLimitForHeap","-Djava.security.egd=file:/dev/./urandom","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005","-javaagent:/opt/skywalking/agent/skywalking-agent.jar","-jar","/app.jar"]
EXPOSE 1088



