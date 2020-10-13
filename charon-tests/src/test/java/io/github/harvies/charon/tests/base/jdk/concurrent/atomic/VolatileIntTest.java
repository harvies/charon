package io.github.harvies.charon.tests.base.jdk.concurrent.atomic;

import io.github.harvies.charon.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * volatile不能保证原子性和互斥性
 *
 * @author harvies
 */
@NotThreadSafe
@Slf4j
public class VolatileIntTest extends AbstractAtomicTest {

    protected volatile int count = 0;

    public static void main(String[] args) {
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
    }
}
