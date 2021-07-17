package io.github.harvies.charon.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "charon-feign")
public interface EchoClient {

    @RequestMapping(method = RequestMethod.GET, value = "/hello1")
    String hello();

}