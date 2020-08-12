package io.github.harvies.eris.base.jdk.concurrent.threadpool;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 2个现线程执行10个任务,执行完再执行main方法
 * @author harvies
 */
public class ThreadPoolExecutorTest {

    private final static BasicThreadFactory threadFactory = new BasicThreadFactory.Builder().namingPattern("aaa").build();
    private final static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 5, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10000), threadFactory);


    public static void main(String[] args) throws InterruptedException {

        int size = 10;
        CountDownLatch latch = new CountDownLatch(size);
        for (int i = 0; i < size; i++) {

            int finalI = i;
            threadPoolExecutor.execute(() -> {
                System.err.println(finalI + ",执行完");

                latch.countDown();
            });
        }

        latch.await();
        System.err.println("main run");
        threadPoolExecutor.shutdown();
    }
}
