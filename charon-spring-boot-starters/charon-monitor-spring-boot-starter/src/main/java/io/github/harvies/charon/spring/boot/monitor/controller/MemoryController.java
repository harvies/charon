package io.github.harvies.charon.spring.boot.monitor.controller;

import io.github.harvies.charon.spring.boot.web.result.ResultDTO;
import io.github.harvies.charon.spring.boot.web.result.Results;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;

@Slf4j
@RestController
@RequestMapping(value = "/memory", produces = MediaType.APPLICATION_JSON_VALUE)
public class MemoryController {

    @RequestMapping(value = "/heapMemoryUsage", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultDTO<MemoryUsage> threadDump() {
        return Results.success(ManagementFactory.getMemoryMXBean().getHeapMemoryUsage());
    }

    @RequestMapping(value = "/nonHeapMemoryUsage", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultDTO<MemoryUsage> getNonHeapMemoryUsage() {
        return Results.success(ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage());
    }

//    @RequestMapping(value = "/info", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResultDTO<JavaInformations> info(ServletContext servletContext) {
//        return Results.success(new JavaInformations(servletContext, true));
//    }
}

