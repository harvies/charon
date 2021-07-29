package io.github.harvies.charon.tests.base.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.*;

@Slf4j
public class ExecutorServiceTest {
    private static final ThreadFactory threadFactory = new ThreadFactoryBuilder()
            .setNameFormat("DemoPool-Thread-%d")//线程名格式
            .setDaemon(false)//默认
            .setPriority(Thread.MAX_PRIORITY)//默认5
            .build();
    private static final ExecutorService executorService = new ThreadPoolExecutor(
            2, 5, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(10), threadFactory, new ThreadPoolExecutor.AbortPolicy() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            log.warn("线程池已满", e);
            super.rejectedExecution(r, e);
        }
    });

    @Test
    public void test() throws InterruptedException {
        int size = 100;
        CountDownLatch countDownLatch = new CountDownLatch(size);
        for (int i = 0; i < size; i++) {
            try {
                executorService.submit(() -> {
                    log.warn("test");
                });
            } catch (Exception e) {
                log.warn("e:", e);
            } finally {
                countDownLatch.countDown();
            }

        }
        countDownLatch.await();
    }
}
