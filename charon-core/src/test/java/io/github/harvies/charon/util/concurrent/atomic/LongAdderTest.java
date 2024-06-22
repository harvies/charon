package io.github.harvies.charon.util.concurrent.atomic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.atomic.LongAdder;


/**
 * LongAdder是JDK1.8开始出现的，所提供的API基本上可以替换掉原先的AtomicLong。
 * LongAdder所使用的思想就是热点分离，这一点可以类比一下ConcurrentHashMap的设计思想。
 * 就是将value值分离成一个数组，当多线程访问时，通过hash算法映射到其中的一个数字进行计数。
 * 而最终的结果，就是这些数组的求和累加。这样一来，就减小了锁的粒度
 *
 * @author harvies
 */
@ThreadSafe
@Slf4j
public class LongAdderTest extends AbstractAtomicTest {
    protected final static LongAdder count = new LongAdder();

    @Test
    public void test() {
        new LongAdderTest().main0();
    }

    @Override
    protected void add() {
        count.increment();
    }

    @Override
    protected void subtract() {
        count.decrement();
    }

    @Override
    protected void print() {
        log.warn("count:{}", count);
        Assertions.assertEquals(count.longValue(),getClientTotal());
    }
}
