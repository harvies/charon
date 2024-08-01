package io.github.harvies.charon.spring.boot.web.controller.system;

import io.github.harvies.charon.model.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;

@Slf4j
@RestController
@RequestMapping(value = "/memory")
public class MemoryController {

    @RequestMapping(value = "/heapMemoryUsage")
    public ApiResult<MemoryUsage> heapMemoryUsage() {
        return ApiResult.success(ManagementFactory.getMemoryMXBean().getHeapMemoryUsage());
    }

    @RequestMapping(value = "/nonHeapMemoryUsage")
    public ApiResult<MemoryUsage> getNonHeapMemoryUsage() {
        return ApiResult.success(ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage());
    }
}

