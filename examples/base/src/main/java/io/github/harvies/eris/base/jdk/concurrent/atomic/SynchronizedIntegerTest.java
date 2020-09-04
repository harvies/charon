package io.github.harvies.eris.base.jdk.concurrent.atomic;

import io.github.harvies.charon.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * volatile不能保证原子性
 *
 * @author harvies
 */
@NotThreadSafe
@Slf4j
public class SynchronizedIntegerTest extends AbstractAtomicTest {

    protected static Integer count = 0;

    public static void main(String[] args) {
        new SynchronizedIntegerTest().main0();
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
    }
}
