package io.github.harvies.charon.test.ribbon;

import io.github.harvies.charon.test.ribbon.client.EchoClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class EchoController {

    @Resource
    private EchoClient echoClient;

    @RequestMapping(value = "/echo/{str}", method = RequestMethod.GET)
    public String echo(@PathVariable(value = "str") String string) {
        log.info("echo str:[{}]", string);
        return echoClient.hello(string);
    }
}