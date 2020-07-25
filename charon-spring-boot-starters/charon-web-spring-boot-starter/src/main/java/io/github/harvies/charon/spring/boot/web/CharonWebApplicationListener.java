package io.github.harvies.charon.spring.boot.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.boot.web.server.WebServer;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * SpringWeb启动完成监听器
 */
@Slf4j
public class CharonWebApplicationListener implements ApplicationListener<ApplicationEvent> {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        log.info("收到事件:[{}]", event.getClass());
        if (event instanceof WebServerInitializedEvent) {
            WebServer webServer = ((WebServerInitializedEvent) event).getWebServer();
            int port = webServer.getPort();
            log.info("web server port is [{}]", port);
            webServer.shutDownGracefully(result -> log.info("shutdownComplete result:[{}]", result));
        }

    }
}
