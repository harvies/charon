package io.github.harvies.charon.dubbo.producer;

import io.github.harvies.charon.dubbo.api.EchoService;
import lombok.SneakyThrows;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService(timeout = 2000)
public class EchoServiceImpl implements EchoService {
    @SneakyThrows
    @Override
    public String echo(String content) {
        Thread.sleep(500);
        return content;
    }
}
