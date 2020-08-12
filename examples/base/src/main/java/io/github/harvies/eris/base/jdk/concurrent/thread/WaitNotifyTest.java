package io.github.harvies.eris.base.jdk.concurrent.thread;

/**
 * demo:线程1总是比线程2先执行完毕
 * 使用到的技术 wait notify
 * 知识点:对象的wait方法会释放锁
 *
 * @author harvies
 */
public class WaitNotifyTest {
    private static final Object lock = new Object();
    private static boolean flag = false;

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            System.err.println("线程1开始执行");
            synchronized (lock) {
                System.err.println("线程1获得锁");
                System.err.println("线程1执行完毕");
                flag = true;
                lock.notifyAll();
            }

        });
        Thread thread2 = new Thread(() -> {
            System.err.println("线程2开始执行");
            synchronized (lock) {
                System.err.println("线程2获得锁");
                try {
                    if (flag == false) {
                        //wait会释放锁
                        lock.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.err.println("线程2执行完毕");
            }
        });
        thread1.setName("thread1");
        thread2.setName("thread2");
        thread1.start();
        thread2.start();
    }
}
