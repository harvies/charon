package io.github.harvies.eris.base.jdk.concurrent;

/**
 * AbstractQueuedSynchronizer 类如其名,抽象的队列式的同步器,AQS 定义了一套多线程访问
 * 共享资源的同步器框架,许多同步类实现都依赖于它,如常用的
 * ReentrantLock/Semaphore/CountDownLatch。
 * <p>
 * 它维护了一个 volatile int state(代表共享资源)和一个 FIFO 线程等待队列(多线程争用资源被
 * 阻塞时会进入此队列)。这里 volatile 是核心关键词,具体 volatile 的语义,在此不述。state 的
 * 访问方式有三种:
 * getState()
 * setState()
 * compareAndSetState()
 * AQS 定义两种资源共享方式
 * Exclusive 独占资源 -ReentrantLock
 * Exclusive(独占,只有一个线程能执行,如 ReentrantLock)
 * Share 共享资源 -Semaphore/CountDownLatch
 * Share(共享,多个线程可同时执行,如 Semaphore/CountDownLatch)。
 *
 * @author harvies
 */
public class AbstractQueuedSynchronizerTest {
}
