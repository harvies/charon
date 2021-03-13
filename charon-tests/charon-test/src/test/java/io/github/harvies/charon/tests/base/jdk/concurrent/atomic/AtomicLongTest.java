package io.github.harvies.charon.tests.base.jdk.concurrent.atomic;

import io.github.harvies.charon.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author harvies
 */
@ThreadSafe
@Slf4j
public class AtomicLongTest extends AbstractAtomicTest {

    protected final static AtomicLong count = new AtomicLong();

    public static void main(String[] args) {
        new AtomicLongTest().main0();
    }


    @Override
    protected void add() {
        count.incrementAndGet();
    }

    @Override
    protected void subtract() {
        count.decrementAndGet();
    }

    @Override
    protected void print() {
        log.warn("count:{}", count);
    }
}
