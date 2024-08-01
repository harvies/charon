package io.github.harvies.charon.util.concurrent.atomic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author harvies
 */
@ThreadSafe
@Slf4j
public class AtomicReferenceTest extends AbstractAtomicTest {
    protected final static AtomicReference<Integer> count = new AtomicReference(0);

    @Test
    public void test() {
        new AtomicReferenceTest().main0();
    }

    @Override
    protected void add() {
        for (; ; ) {
            Integer integer = count.get();
            if (count.compareAndSet(integer, integer + 1)) {
                return;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void subtract() {
        for (; ; ) {
            Integer integer = count.get();
            if (count.compareAndSet(integer, integer - 1)) {
                return;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void print() {
        log.warn("count:{}", count);
        Assertions.assertEquals(count.get(),getClientTotal());
    }
}
