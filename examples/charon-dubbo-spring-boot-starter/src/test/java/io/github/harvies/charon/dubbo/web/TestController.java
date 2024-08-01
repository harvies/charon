package io.github.harvies.charon.dubbo.web;

import io.github.harvies.charon.dubbo.api.EchoService;
import io.github.harvies.charon.model.ApiResult;
import io.github.harvies.charon.util.RandomUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @DubboReference(timeout = 1000, injvm = false)
    private EchoService echoService;

    @RequestMapping(value = "/hello")
    public ApiResult<String> hello() {
        return ApiResult.success(echoService.echo(RandomUtils.uuid()));
    }
}
