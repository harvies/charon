package io.github.harvies.eris.base.jdk.concurrent.atomic;

import io.github.harvies.eris.base.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * AtomicStampedReference用来解决ABA问题
 * 底层使用包含版本号(stamp)和引用(ref)的对象
 *
 * @author harvies
 */
@Slf4j
@ThreadSafe
public class AtomicStampedReferenceTest extends AbstractAtomicTest {
    public static void main(String[] args) {
        new AtomicStampedReferenceTest().main0();
    }

    private static final AtomicStampedReference<Integer> count = new AtomicStampedReference<>(0, 0);

    @Override
    protected void add() {
        for (; ; ) {
            Integer integer = count.getReference();
            int stamp = count.getStamp();
            if (count.compareAndSet(integer, integer + 1, stamp, stamp + 1)) {
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
            Integer integer = count.getReference();
            int stamp = count.getStamp();
            if (count.compareAndSet(integer, integer - 1, stamp, stamp + 1)) {
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
        log.warn("count:{} stamp:{}", count.getReference(), count.getStamp());
    }
}
