package io.github.harvies.eris.base.jdk.concurrent.thread;

/**
 * 2. sleep() 和 wait() 有什么区别?
 * sleep 就是正在执行的线程主动让出 cpu，cpu 去执行其他线程，
 * 在 sleep 指定的时间过后，cpu 才会回到这个线程上继续往下执行，
 * 如果当前线程进入了同步锁，sleep 方法并不会释放锁，即使当前线程使用 sleep 方法让出了 cpu，
 * 但其他被同步锁挡住了的线程也无法得到执行。wait 是指在一个已经进入了同步锁的线程内，
 * 让自己暂时让出同步锁，以便其他正在等待此锁的线程可以得到同步锁并运行，只有其他线程调用了 notify 方法（notify 并不释放锁，
 * 只是告诉调用过 wait 方法的线程可以去参与获得锁的竞争了，但不是马上得到锁，因为锁还在别人手里，别人还没释放。
 * 如果 notify 方法后面的代码还有很多，需要这些代码执行完后才会释放锁，可以在 notfiy 方法后增加一个等待和一些代码，看看效果），
 * 调用 wait 方法的线程就会解除 wait 状态和程序可以再次得到锁后继续向下运行。
 *
 * @author harvies
 */
public class SleepTest {

}
