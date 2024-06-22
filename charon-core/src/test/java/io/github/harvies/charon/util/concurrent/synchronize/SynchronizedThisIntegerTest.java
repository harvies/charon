package io.github.harvies.charon.util.concurrent.synchronize;

import io.github.harvies.charon.util.concurrent.atomic.AbstractAtomicTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.concurrent.NotThreadSafe;

/**
 * 同步同一把锁
 * volatile不能保证原子性
 *
 * @author harvies
 */
@NotThreadSafe
@Slf4j
public class SynchronizedThisIntegerTest extends AbstractAtomicTest {

    protected static Integer count = 0;

    @Test
    public void test() {
        new SynchronizedThisIntegerTest().main0();
    }

    @Override
    protected void add() {
        synchronized (this) {
            /**
             * count++ 等于  count=count+1;
             */
            count++;
        }
    }

    @Override
    protected void subtract() {
        synchronized (this) {
            /**
             * count++ 等于  count=count+1;
             */
            count--;
        }
    }

    @Override
    protected void print() {
        log.warn("count:{}", count);
        Assertions.assertEquals(count.longValue(),getClientTotal());
    }
}
