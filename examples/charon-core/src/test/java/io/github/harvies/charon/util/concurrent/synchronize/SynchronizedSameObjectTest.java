package io.github.harvies.charon.util.concurrent.synchronize;

import io.github.harvies.charon.util.concurrent.atomic.AbstractAtomicTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.concurrent.ThreadSafe;

/**
 *
 * 同步同一把锁（静态/非静态都行）
 *
 * synchronized提供了两种主要特性：互斥（mutual exclusion） 和可见性（visibility）。
 * 互斥性:即一次只允许一个线程持有某个特定的锁
 * 可见性:释放锁之前对共享数据做出的更改对于随后获得该锁的另一个线程是可见的
 *
 * @author harvies
 */
@ThreadSafe
@Slf4j
public class SynchronizedSameObjectTest extends AbstractAtomicTest {

    //同一把锁
    protected Object object = new Object();
    protected static Integer count = 0;

    @Test
    public void test() {
        new SynchronizedSameObjectTest().main0();
    }


    @Override
    protected void add() {
        /**
         * synchronized则可以保证变量修改的可见性和互斥性
         */
        synchronized (object) {
            count++;
        }
    }

    @Override
    protected void subtract() {
        /**
         * synchronized则可以保证变量修改的可见性和互斥性
         */
        synchronized (object) {
            count--;
        }
    }

    @Override
    protected void print() {
        log.warn("count:{}", count);
        Assertions.assertEquals(count.intValue(), getClientTotal());
    }
}
