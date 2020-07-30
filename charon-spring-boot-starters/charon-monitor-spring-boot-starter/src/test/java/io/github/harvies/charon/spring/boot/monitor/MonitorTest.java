package io.github.harvies.charon.spring.boot.monitor;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.bull.javamelody.internal.model.HeapHistogram;
import net.bull.javamelody.internal.model.VirtualMachine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
public class MonitorTest {
    @SneakyThrows
    @Test
    public void test() {
        HeapHistogram heapHistogram = VirtualMachine.createHeapHistogram();
        Assertions.assertNotNull(heapHistogram);
    }
}
