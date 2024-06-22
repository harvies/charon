package io.github.harvies.charon.util.concurrent.synchronize;

import io.github.harvies.charon.util.concurrent.atomic.AbstractAtomicTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.concurrent.NotThreadSafe;

/**
 * 同步非同一把锁
 *
 * volatile不能保证原子性
 *
 * @author harvies
 */
@NotThreadSafe
@Slf4j
public class SynchronizedStaticFieldIntegerTest extends AbstractAtomicTest {

    protected static Integer count = 0;

    @Test
    public void test() {
        new SynchronizedStaticFieldIntegerTest().main0();
    }

    @Override
    protected void add() {
        /**
         * 同步的对象不在是原来的对象了
         */
        synchronized (count) {
            /**
             * count++ 等于  count=count+1;
             */
            count++;
        }
    }

    @Override
    protected void subtract() {
        /**
         * 同步的对象不在是原来的对象了
         */
        synchronized (count) {
            /**
             * count++ 等于  count=count+1;
             */
            count--;
        }
    }

    @Override
    protected void print() {
        log.warn("count:{}", count);
        Assertions.assertNotEquals(count.intValue(),getClientTotal());
    }
}
