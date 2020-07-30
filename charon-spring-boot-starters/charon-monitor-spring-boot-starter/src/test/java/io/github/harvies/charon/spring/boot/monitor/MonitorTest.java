package io.github.harvies.charon.spring.boot.monitor;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.bull.javamelody.internal.model.HeapHistogram;
import net.bull.javamelody.internal.model.PID;
import net.bull.javamelody.internal.model.VirtualMachine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
public class MonitorTest {
    /**
     * 获取内存直方图
     */
    @SneakyThrows
    @Test
    public void createHeapHistogram() {
        HeapHistogram heapHistogram = VirtualMachine.createHeapHistogram();
        Assertions.assertNotNull(heapHistogram);
    }

    /**
     * 获取PID
     */
    @SneakyThrows
    @Test
    public void getPID() {
        String pid = PID.getPID();
        Assertions.assertNotNull(pid);
    }
}
