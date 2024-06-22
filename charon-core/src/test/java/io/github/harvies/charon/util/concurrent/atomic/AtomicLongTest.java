package io.github.harvies.charon.util.concurrent.atomic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author harvies
 */
@ThreadSafe
@Slf4j
public class AtomicLongTest extends AbstractAtomicTest {

    protected final static AtomicLong count = new AtomicLong();
    @Test
    public void test() {
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
        Assertions.assertEquals(count.get(),getClientTotal());
    }
}
