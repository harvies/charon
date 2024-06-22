package io.github.harvies.charon.util.concurrent.atomic;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

/**
 * AtomicFieldUpdaterTest
 *
 * @author harvies
 *
//<a href="https://www.logicbig.com/tutorials/core-java-tutorial/java-multi-threading/atomic-field-updater.html">...</a>
 */
@ThreadSafe
@Slf4j
public class AtomicFieldUpdaterTest extends AbstractAtomicTest {

    @Getter
    static class Count {
        volatile int count;
    }

   private static final AtomicIntegerFieldUpdater<AtomicFieldUpdaterTest.Count> atomicIntegerFieldUpdater =
            AtomicIntegerFieldUpdater.newUpdater(AtomicFieldUpdaterTest.Count.class, "count");

   private final Count count = new Count();

    @Test
    public void test() {
        new AtomicFieldUpdaterTest().main0();
    }

    @Override
    protected void add() {

        atomicIntegerFieldUpdater.incrementAndGet(count);
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void subtract() {
        atomicIntegerFieldUpdater.decrementAndGet(count);
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void print() {
        log.warn("count:{}", count);
        Assertions.assertEquals(atomicIntegerFieldUpdater.get(count), getClientTotal());
    }
}
