package io.github.harvies.eris.base.jdk.concurrent.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 分3轮每轮A打印3次,B打印5次,C打印10次
 *
 * @author harvies
 */
public class ReentrantLockConditionTest2 {

    public static void main(String[] args) {

        ReentrantLock reentrantLock = new ReentrantLock();
        Condition conditionA = reentrantLock.newCondition();
        Condition conditionB = reentrantLock.newCondition();
        Condition conditionC = reentrantLock.newCondition();
        final int[] current = {1};

        new Thread(() -> {
            for (int i = 0; i < 3; i++) {

                reentrantLock.lock();
                try {
                    if (current[0] != 1) {
                        conditionA.await();
                    }
                    for (int j = 0; j < 3; j++) {
                        System.err.println("A" + "-" + j + "-" + i);
                    }

                    current[0] = 2;
                    conditionB.signal();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    reentrantLock.unlock();
                }

            }

        }).start();
        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                reentrantLock.lock();
                try {
                    if (current[0] != 2) {
                        conditionB.await();
                    }
                    for (int j = 0; j < 5; j++) {
                        System.err.println("B" + "-" + j + "-" + i);
                    }
                    current[0] = 3;
                    conditionC.signal();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    reentrantLock.unlock();
                }
            }

        }).start();
        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                reentrantLock.lock();
                try {
                    if (current[0] != 3) {
                        conditionC.await();
                    }
                    for (int j = 0; j < 10; j++) {
                        System.err.println("C" + "-" + j + "-" + i);
                    }
                    current[0] = 1;
                    conditionA.signal();

                    System.err.println("----------");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    reentrantLock.unlock();
                }
            }

        }).start();
    }
}
