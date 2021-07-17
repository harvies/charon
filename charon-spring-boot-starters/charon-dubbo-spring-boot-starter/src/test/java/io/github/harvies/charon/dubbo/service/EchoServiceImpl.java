package io.github.harvies.charon.dubbo.service;

import io.github.harvies.charon.dubbo.api.EchoService;
import lombok.SneakyThrows;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.Method;

@DubboService(timeout = 2000, methods = {
        @Method(name = "echo", retries = 5)
})
public class EchoServiceImpl implements EchoService {

    @SneakyThrows
    @Override
    public String echo(String content) {
        System.out.println("aaaaaa");
        Thread.sleep(3000);
        return content;
    }
}
