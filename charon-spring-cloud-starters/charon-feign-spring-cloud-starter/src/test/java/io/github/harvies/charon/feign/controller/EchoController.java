package io.github.harvies.charon.feign.controller;

import io.github.harvies.charon.feign.client.EchoClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class EchoController {

    @Resource
    private EchoClient echoClient;

    @GetMapping("/hello")
    public String hello() {
        return echoClient.hello();
    }

    @GetMapping("/hello1")
    public String hello1() {
        return "hello";
    }
}
