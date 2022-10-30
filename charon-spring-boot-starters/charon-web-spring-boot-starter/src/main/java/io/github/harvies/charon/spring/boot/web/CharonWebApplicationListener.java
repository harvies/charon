package io.github.harvies.charon.spring.boot.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * SpringWeb启动完成监听器
 */
@Slf4j
public class CharonWebApplicationListener implements ApplicationListener<ApplicationEvent> {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        log.debug("Charon Spring Boot Web收到事件:[{}]", event);
        if (event instanceof ServletWebServerInitializedEvent) {
            WebServer webServer = ((ServletWebServerInitializedEvent) event).getWebServer();
            int port = webServer.getPort();
            log.info("web server started web server port is [{}]", port);
//            webServer.shutDownGracefully(result -> log.info("shutdownComplete result:[{}]", result));
        }

    }
}
