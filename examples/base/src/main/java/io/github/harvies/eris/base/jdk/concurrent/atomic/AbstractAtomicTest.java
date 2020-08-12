package io.github.harvies.eris.base.jdk.concurrent.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author harvies
 */
@Slf4j
public abstract class AbstractAtomicTest {
    /**
     * 并发数
     */
    private final static int threadTotal = 10;
    /**
     * 请求数
     */
    private final static int clientTotal = 10000;
    private final static CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
    private final static ExecutorService executorService = Executors.newFixedThreadPool(threadTotal);

    protected void main0() {
        long beginTimeMillis = System.currentTimeMillis();
        for (int i = 0; i < clientTotal; i++) {

            executorService.execute(() -> {
                try {
                    add();
                    subtract();
                    add();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        print();
        log.warn("耗时:{}ms", System.currentTimeMillis() - beginTimeMillis);
        executorService.shutdown();
    }

    /**
     * 计数
     */
    protected abstract void add();

    protected abstract void subtract();

    /**
     * 打印结果
     */
    protected abstract void print();
}
