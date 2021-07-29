package io.github.harvies.charon.tests.base.jdk.concurrent;

/**
 * synchronized 和 ReentrantLock 的区别
 * 两者的共同点：
 * 1. 都是用来协调多线程对共享对象、变量的访问
 * 2. 都是可重入锁，同一线程可以多次获得同一个锁3. 都保证了可见性和互斥
 * <p>
 * 两者的不同点：
 * 1. ReentrantLock 显示的获得、释放锁，synchronized 隐式获得释放锁
 * 2. ReentrantLock 可响应中断、可轮回，synchronized 是不可以响应中断的，为处理锁的
 * 不可用性提供了更高的灵活性
 * 3. ReentrantLock 是 API 级别的，synchronized 是 JVM 级别的
 * 4. ReentrantLock 可以实现公平锁
 * 5. ReentrantLock 通过 Condition 可以绑定多个条件
 * 6. 底层实现不一样， synchronized 是同步阻塞，使用的是悲观并发策略，lock 是同步非阻
 * 塞，采用的是乐观并发策略
 * 7. Lock 是一个接口，而 synchronized 是 Java 中的关键字，synchronized 是内置的语言
 * 实现。
 * 8. synchronized 在发生异常时，会自动释放线程占有的锁，因此不会导致死锁现象发生；
 * 而 Lock 在发生异常时，如果没有主动通过 unLock()去释放锁，则很可能造成死锁现象，
 * 因此使用 Lock 时需要在 finally 块中释放锁。
 * 9. Lock 可以让等待锁的线程响应中断，而 synchronized 却不行，使用 synchronized 时，
 * 等待的线程会一直等待下去，不能够响应中断。
 * 10. 通过 Lock 可以知道有没有成功获取锁，而 synchronized 却无法办到。
 * 11. Lock 可以提高多个线程进行读操作的效率，既就是实现读写锁等。
 *
 * @author harvies
 */
public class SynchronizedCompareReentrantLock {
}
