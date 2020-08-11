package io.github.harvies.charon.dubbo.producer;

import io.github.harvies.charon.dubbo.api.EchoService;
import org.apache.dubbo.config.annotation.Service;

@Service
public class EchoServiceImpl implements EchoService {
    @Override
    public String echo(String content) {
        return content;
    }
}
