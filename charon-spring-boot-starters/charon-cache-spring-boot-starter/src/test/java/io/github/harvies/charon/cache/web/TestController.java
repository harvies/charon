package io.github.harvies.charon.cache.web;

import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import io.github.harvies.charon.cache.service.TestService;
import io.github.harvies.charon.result.ResultDTO;
import io.github.harvies.charon.result.Results;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Resource
    private TestService testService;

    @RequestMapping(value = "/hello")
    public ResultDTO<String> hello() {
        return Results.success(testService.echo("111"));
    }
}
