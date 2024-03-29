package io.github.harvies.charon.spring.boot.web.controller.system;

import io.github.harvies.charon.model.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/runtime")
public class RuntimeController {

    @RequestMapping(value = "/systemProperties")
    public ApiResult<Map<String, String>> systemProperties() {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        return ApiResult.success(runtimeMXBean.getSystemProperties());
    }

}
