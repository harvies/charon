package io.github.harvies.charon.feign.controller;

import io.github.harvies.charon.feign.client.EchoClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class EchoController {

    @Resource
    private EchoClient echoClient;

    @GetMapping("/echo/{str}")
    public String hello(@PathVariable(value = "str") String str) {
        return echoClient.hello(str);
    }

    @GetMapping("/echo1/{str}")
    public String hello1(@PathVariable(value = "str") String str) {
        return str;
    }
}
