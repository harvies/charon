package io.github.harvies.charon.spring.boot.web.controller.system;

import io.github.harvies.charon.model.ApiResult;
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
    public ApiResult<ThreadInfo[]> threadDump() {
        return ApiResult.success(ManagementFactory.getThreadMXBean().dumpAllThreads(true, true));
    }

}
