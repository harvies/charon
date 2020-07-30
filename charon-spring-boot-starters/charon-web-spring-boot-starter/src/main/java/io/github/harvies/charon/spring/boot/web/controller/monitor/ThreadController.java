package io.github.harvies.charon.spring.boot.web.controller.monitor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;

@Slf4j
@RestController
@RequestMapping(value = "/thread", produces = MediaType.APPLICATION_JSON_VALUE)
public class ThreadController {

    @RequestMapping(value = "/dumpAllThreads", produces = MediaType.APPLICATION_JSON_VALUE)
    public ThreadInfo[] threadDump() {
        return ManagementFactory.getThreadMXBean().dumpAllThreads(true, true);
    }

}
