package io.github.harvies.charon.util.concurrent.lock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        new Thread(ReentrantLockTest::test, "threadA").start();
        new Thread(ReentrantLockTest::test, "threadB").start();
    }

    private static void test() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "-获取到锁");
            System.out.println(Thread.currentThread().getName() + "-业务逻辑处理");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            System.out.println(Thread.currentThread().getName() + "-释放锁");
            lock.unlock();
        }
    }

}
