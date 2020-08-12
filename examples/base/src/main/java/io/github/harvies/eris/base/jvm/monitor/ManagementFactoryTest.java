package io.github.harvies.eris.base.jvm.monitor;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;

/**
 * @author harvies
 */
public class ManagementFactoryTest {
    public static void main(String[] args) {
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        System.err.println(memoryMXBean.getHeapMemoryUsage());
        String version = ManagementFactory.getOperatingSystemMXBean().getVersion();
        System.err.println("version:" + version);
    }
}
