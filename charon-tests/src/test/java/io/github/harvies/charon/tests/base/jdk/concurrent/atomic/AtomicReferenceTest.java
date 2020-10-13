package io.github.harvies.charon.tests.base.jdk.concurrent.atomic;

import io.github.harvies.charon.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author harvies
 */
@ThreadSafe
@Slf4j
public class AtomicReferenceTest extends AbstractAtomicTest {
    protected final static AtomicReference<Integer> count = new AtomicReference(0);

    public static void main(String[] args) {
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
    }
}
