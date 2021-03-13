package io.github.harvies.charon.tests.base.jdk.concurrent.thread;

/**
 * 调用该方法的线程进入 WAITING 状态,只有等待另外线程的通知或被中断才会返回,需要注意的
 * 是调用 wait()方法后,会释放对象的锁。因此,wait 方法一般用在同步方法或同步代码块中。
 *
 * @author harvies
 */
public class IllegalMonitorStateExceptionTest {
    private static Object lock = new Object();

    public static void main(String[] args) {
        try {
            lock.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
