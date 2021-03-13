package io.github.harvies.charon.tests.base.jdk.concurrent.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @author harvies
 */
@Slf4j
public class DeadLockDemo {

    public static void main(String[] args) {

        Object lockA = new Object();
        Object lockB = new Object();

        new Thread(() -> {
            synchronized (lockA) {
                log.info("get lockA");
                synchronized (lockB) {
                    log.info("get lockB");
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (lockB) {
                log.info("get lockB");
                synchronized (lockA) {
                    log.info("get lockA");
                }
            }
        }).start();
    }
}
