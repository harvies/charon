package io.github.harvies.charon.tests.base.jdk.concurrent.atomic;

import io.github.harvies.charon.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 首 先 说 明 , 此 处 AtomicInteger , 一 个 提 供 原 子 操 作 的 Integer 的 类 , 常 见 的 还 有
 * AtomicBoolean、AtomicInteger、AtomicLong、AtomicReference 等,他们的实现原理相同,
 * 区别在与运算对象类型的不同。令人兴奋地,还可以通过 AtomicReference<V>将一个对象的所
 * 有操作转化成原子操作。
 * 我们知道,在多线程程序中,诸如++i 或 i++等运算不具有原子性,是不安全的线程操作之一。
 * 通常我们会使用 synchronized 将该操作变成一个原子操作,但 JVM 为此类操作特意提供了一些
 * 同步类,使得使用更方便,且使程序运行效率变得更高。通过相关资料显示,通常 AtomicInteger
 * 的性能是 ReentantLock 的好几倍。
 *
 * @author harvies
 */
@ThreadSafe
@Slf4j
public class AtomicIntegerTest extends AbstractAtomicTest {

    protected final static AtomicInteger count = new AtomicInteger();

    public static void main(String[] args) {
        new AtomicIntegerTest().main0();
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
