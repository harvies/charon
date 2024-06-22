package io.github.harvies.charon.util.concurrent.thread;

/**
 * 4. 当一个线程进入一个对象的一个 synchronized 方法后，其它线程是否可进入此对象的其它方法?
 * 其他方法前是否加了 synchronized 关键字，如果没加，则能。
 * 如果这个方法内部调用了 wait，则可以进入其他 synchronized 方法。
 * 如果其他个方法都加了 synchronized 关键字，并且内部没有调用 wait，则不能。
 * 如果其他方法是 static，它用的同步锁是当前类的字节码，与非静态的方法不能同步，因为非静态的方法用的是 this。
 *
 * 1.sleep()不会释放当前占有的锁,sleep(long)会导致
 *
 * 2.sleep()线程进入 TIMED-WATING 状态,而 wait()方法会导致当前线程进入 WATING 状态
 *
 * @author harvies
 */
public class WaitSleepTest {
    private synchronized void test1() {
        System.err.println(Thread.currentThread().getName() + ":" + "test1");
        try {
            /**
             * wait会释放锁,sleep不会
             */
            this.wait();
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println(Thread.currentThread().getName() + ":" + "test1 end");
    }

    private synchronized void test2() {
        System.err.println(Thread.currentThread().getName() + ":" + "test2");
        this.notify();
        System.err.println(Thread.currentThread().getName() + ":" + "test2 end");
    }

    public static void main(String[] args) {
        WaitSleepTest waitSleepTest = new WaitSleepTest();
        Thread thread1 = new Thread(() -> {
            waitSleepTest.test1();
        }, "thread1");

        Thread thread2 = new Thread(() -> {
            waitSleepTest.test2();
        }, "thread2");
        thread1.start();
        thread2.start();
    }
}
