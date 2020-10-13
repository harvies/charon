package io.github.harvies.charon.tests.base.jdk.concurrent.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁，也叫做递归锁，指的是同一线程 外层函数获得锁之后 ，内层递归函数仍然有获取该锁的代码，但不受影响。
 * 在JAVA环境下 ReentrantLock 和synchronized 都是 可重入锁
 * 出处:http://ifeve.com/java_lock_see4/
 *
 * @author harvies
 */
public class ReentrantLockTest implements Runnable {
    ReentrantLock lock = new ReentrantLock();

    public void get() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getId());
            set();
        } finally {
            lock.unlock();
        }
    }

    public void set() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getId());
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        get();
    }

    public static void main(String[] args) {
        ReentrantLockTest ss = new ReentrantLockTest();
        new Thread(ss).start();
        new Thread(ss).start();
        new Thread(ss).start();
    }
}
