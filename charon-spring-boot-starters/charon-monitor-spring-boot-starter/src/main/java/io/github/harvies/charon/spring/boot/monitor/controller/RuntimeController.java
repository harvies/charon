package io.github.harvies.charon.spring.boot.monitor.controller;

import io.github.harvies.charon.spring.boot.web.result.ResultDTO;
import io.github.harvies.charon.spring.boot.web.result.Results;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/runtime", produces = MediaType.APPLICATION_JSON_VALUE)
public class RuntimeController {

    @RequestMapping(value = "/systemProperties")
    public ResultDTO<Map<String, String>> systemProperties() {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        return Results.success(runtimeMXBean.getSystemProperties());
    }

}
