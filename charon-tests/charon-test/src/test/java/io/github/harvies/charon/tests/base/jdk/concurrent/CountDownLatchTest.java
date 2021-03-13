package io.github.harvies.charon.tests.base.jdk.concurrent;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * CountDownLatch（线程计数器,闭锁 ）
 * <p>
 * CountDownLatch 类位于 java.util.concurrent 包下,利用它可以实现类似计数器的功能。比如有一个任务A，它要等待其他4个任务执行完毕之后才能执行，此时就可以利用CountDownLatch来实现这种功能了。
 * 出处:https://www.cnblogs.com/dolphin0520/p/3920397.html
 *
 * @author harvies
 */
public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        int threadNum = 5;
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("test-pool").build();
        ExecutorService executorService = Executors.newFixedThreadPool(threadNum, threadFactory);

        for (int i = 0; i < threadNum; i++) {
            executorService.execute(() -> {
                try {
                    System.err.println(Thread.currentThread().getName() + "  run");
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        Thread.currentThread().setName("test-main");
        countDownLatch.await();
        executorService.shutdown();
        System.err.println("exit");

    }
}
