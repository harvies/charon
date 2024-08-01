package io.github.harvies.charon.agent;

import lombok.extern.slf4j.Slf4j;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class Metric {
    private static final long MB = 1048576L;

    public static void printMemoryInfo() {
        MemoryMXBean memory = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapMemory = memory.getHeapMemoryUsage();

        String info = String.format("\ninit: %s\t max: %s\t used: %s\t committed: %s\t use rate: %s\n",
                heapMemory.getInit() / MB + "MB",
                heapMemory.getMax() / MB + "MB", heapMemory.getUsed() / MB + "MB",
                heapMemory.getCommitted() / MB + "MB",
                heapMemory.getUsed() * 100 / heapMemory.getCommitted() + "%"

        );
        log.info("headMemory info:[{}]", info);

        MemoryUsage nonHeapMemory = memory.getNonHeapMemoryUsage();

        info = String.format("init: %s\t max: %s\t used: %s\t committed: %s\t use rate: %s\n",
                nonHeapMemory.getInit() / MB + "MB",
                nonHeapMemory.getMax() / MB + "MB", nonHeapMemory.getUsed() / MB + "MB",
                nonHeapMemory.getCommitted() / MB + "MB",
                nonHeapMemory.getUsed() * 100 / nonHeapMemory.getCommitted() + "%"

        );
        log.info("nonHeapMemory info:[{}]", info);
    }

    public static void printGCInfo() {
        List<GarbageCollectorMXBean> garbageCollectorMXBeanList = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean garbage : garbageCollectorMXBeanList) {
            String info = String.format("name: %s\t count:%s\t took:%s\t pool name:%s",
                    garbage.getName(),
                    garbage.getCollectionCount(),
                    garbage.getCollectionTime(),
                    Arrays.deepToString(garbage.getMemoryPoolNames()));
            log.info("garbageCollector info:[{}]", info);
        }
    }
}