package io.github.harvies.charon.tests.base.jdk.concurrent.lock;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;


/**
 * 提供了引用变量的读写原子性操作
 * from:https://blog.csdn.net/u014507083/article/details/70432180
 *
 * @author harvies
 */
public class AtomicReferenceTest {
    private static final int threadNum = 10;
    public static final CountDownLatch latch = new CountDownLatch(threadNum);

    public static void main(String[] args) throws Exception {
        AtomicReference<String> flag = new AtomicReference<>("cx");
        for (int i = 0; i < threadNum; i++) {
            new MyThread(flag, i + "").start();
        }
        latch.await();
        System.out.println("main:" + flag.get());
    }
}

class MyThread extends Thread {
    private AtomicReference<String> flag;

    public MyThread(AtomicReference<String> flag, String name) {
        super(name);
        this.flag = flag;
    }

    @Override
    public void run() {
        int time = new Random().nextInt(500);
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (flag.compareAndSet("cx", "gx")) {
            System.out.println(Thread.currentThread().getName() + ":success");
        } else {
            System.out.println(Thread.currentThread().getName() + ":failed");
        }
        AtomicReferenceTest.latch.countDown();
    }
}
