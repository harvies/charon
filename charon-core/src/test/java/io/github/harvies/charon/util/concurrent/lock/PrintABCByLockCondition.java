package io.github.harvies.charon.util.concurrent.lock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PrintABCByLockCondition {
    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock();
        Condition conditionA = lock.newCondition();
        Condition conditionB = lock.newCondition();
        Condition conditionC = lock.newCondition();

        AtomicInteger state = new AtomicInteger();

        Thread threadA = new Thread(() -> {
            for (; ; ) {
                try {
                    lock.lock();
                    if (state.get() % 3 == 0) {
                        System.out.println(Thread.currentThread().getName() + "A");
                        state.incrementAndGet();
                        conditionB.signal();
                    } else {
                        try {
                            conditionA.await();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } finally {
                    lock.unlock();
                }
            }


        },"ThreadA-");
        Thread threadB = new Thread(() -> {
            for (; ; ) {
                try {
                    lock.lock();
                    if (state.get() % 3 == 1) {
                        System.out.println(Thread.currentThread().getName() +"B");
                        state.incrementAndGet();
                        conditionC.signal();
                    } else {
                        try {
                            conditionB.await();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } finally {
                    lock.unlock();
                }
            }
        },"ThreadB-");

        Thread threadC = new Thread(() -> {
            for (; ; ) {
                try {
                    lock.lock();
                    if (state.get() % 3 == 2) {
                        System.out.println(Thread.currentThread().getName() +"C");
                        state.incrementAndGet();
                        conditionA.signal();
                    } else {
                        try {
                            conditionC.await();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } finally {
                    lock.unlock();
                }
            }
        },"ThreadC-");
        threadA.start();
        threadB.start();
        threadC.start();
    }
}
