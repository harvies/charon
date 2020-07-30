package io.github.harvies.charon.spring.boot.web.controller.monitor;

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
    public MemoryUsage threadDump() {
        return ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
    }

    @RequestMapping(value = "/nonHeapMemoryUsage", produces = MediaType.APPLICATION_JSON_VALUE)
    public MemoryUsage getNonHeapMemoryUsage() {
        return ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage();
    }
}
