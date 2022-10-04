package io.github.harvies.charon.cache.web;

import io.github.harvies.charon.cache.request.TestRequest;
import io.github.harvies.charon.cache.service.TestService;
import io.github.harvies.charon.model.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ApiResult<TestRequest> hello(@RequestBody TestRequest request) {
        return ApiResult.success(testService.echo(request));
    }
}
