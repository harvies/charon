package io.github.harvies.charon.spring.boot.web.controller.system;

import io.github.harvies.charon.result.ResultDTO;
import io.github.harvies.charon.result.Results;
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
    public ResultDTO<MemoryUsage> heapMemoryUsage() {
        return Results.success(ManagementFactory.getMemoryMXBean().getHeapMemoryUsage());
    }

    @RequestMapping(value = "/nonHeapMemoryUsage")
    public ResultDTO<MemoryUsage> getNonHeapMemoryUsage() {
        return Results.success(ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage());
    }
}

