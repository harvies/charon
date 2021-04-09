package io.github.harvies.charon.spring.boot.web.controller.system;

import io.github.harvies.charon.result.ResultDTO;
import io.github.harvies.charon.result.Results;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;

@Slf4j
@RestController
@RequestMapping(value = "/thread")
public class ThreadController {

    @RequestMapping(value = "/dumpAllThreads")
    public ResultDTO<ThreadInfo[]> threadDump() {
        return Results.success(ManagementFactory.getThreadMXBean().dumpAllThreads(true, true));
    }

}
