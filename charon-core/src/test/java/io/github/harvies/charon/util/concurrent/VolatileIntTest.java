package io.github.harvies.charon.util.concurrent;

import io.github.harvies.charon.util.concurrent.atomic.AbstractAtomicTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.concurrent.NotThreadSafe;

/**
 * volatile不能保证原子性和互斥性
 *
 * @author harvies
 */
@NotThreadSafe
@Slf4j
public class VolatileIntTest extends AbstractAtomicTest {

    protected volatile int count = 0;
    @Test
    public void test() {
        new VolatileIntTest().main0();
    }


    @Override
    protected void add() {
        /**
         * volatile不能保证原子性和互斥性,i++操作是非原子性的,多个线程不互斥
         */
        count++;
    }

    @Override
    protected void subtract() {
        /**
         * volatile不能保证原子性和互斥性,i++操作是非原子性的,多个线程不互斥
         */
        count--;
    }

    @Override
    protected void print() {
        log.warn("count:{}", count);
        Assertions.assertEquals(count,getClientTotal());
    }
}
