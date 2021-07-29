package io.github.harvies.charon.test.ribbon.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "charon-sleuth")
public interface EchoClient {

    @RequestMapping(method = RequestMethod.GET, value = "/echo/{str}")
    String hello(@PathVariable(value = "str") String str);

}