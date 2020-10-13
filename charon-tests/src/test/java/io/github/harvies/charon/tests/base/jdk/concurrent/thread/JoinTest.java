package io.github.harvies.charon.tests.base.jdk.concurrent.thread;

/**
 * @author harvies
 * 面试题:现在有T1、T2、T3三个线程，你怎样保证T2在T1执行完后执行，T3在T2执行完后执行？
 */
public class JoinTest {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            System.err.println("线程1执行完毕!");
        });
        Thread thread2 = new Thread(() -> {
            try {
                thread1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.err.println("线程2执行完毕!");
        });
        Thread thread3 = new Thread(() -> {
            try {
                /**
                 * 参数是毫秒,意思是thread3等待2s后,thread2还没执行就先执行,不加参数就会一直等待,可看下源码
                 */
                thread2.join(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.err.println("线程3执行完毕!");
        });
        thread1.setName("thread1");
        thread2.setName("thread2");
        thread3.setName("thread3");
        thread3.start();
        thread2.start();
        thread1.start();
    }
}

