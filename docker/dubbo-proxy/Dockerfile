FROM harvies/jdk8:latest
MAINTAINER cr0n6e@gmail.com
WORKDIR /source
RUN git clone https://github.com/harvies/dubbo-proxy.git
WORKDIR /source/dubbo-proxy
RUN mvn clean package -DskipTests && cp target/dubbo-proxy-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["/sbin/tini", "--","java","-XX:+UnlockExperimentalVMOptions","-XX:+UseCGroupMemoryLimitForHeap","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
EXPOSE 8000

