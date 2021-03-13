package io.github.harvies.charon.tests.base.jdk.concurrent.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author harvies
 */
public class ReentrantLockConditionTest {
    private static ReentrantLock reentrantLock = new ReentrantLock();
    /**
     * 一个锁可有多个条件对象
     */
    private static Condition condition = reentrantLock.newCondition();

    /**
     * 业务代码是否执行完标志
     */
    private static boolean executed = false;

    public static void main(String[] args) {
        new Thread(() -> {
            System.err.println("线程 " + Thread.currentThread().getName() + " 启动");
            try {
                reentrantLock.lock();
                System.err.println("线程 " + Thread.currentThread().getName() + " 获取到锁,开始执行业务代码");
                Thread.sleep(2000);
                System.err.println("线程 " + Thread.currentThread().getName() + " 业务代码执行完毕,开始通知其他线程处理");
                executed = true;
                condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }

        }).start();

        new Thread(() -> {
            System.err.println("线程 " + Thread.currentThread().getName() + " 启动");
            try {
                reentrantLock.lock();
                /**
                 * 如果业务代码没执行完,等待
                 */
                if (!executed) {
                    System.err.println("业务代码没执行完,等待");
                    condition.await();
                }
                System.err.println("线程 " + Thread.currentThread().getName() + " 获取到锁,开始执行其他任务");
                Thread.sleep(2000);
                System.err.println("线程 " + Thread.currentThread().getName() + " 其他任务执行完毕");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
        }).start();
    }
}
