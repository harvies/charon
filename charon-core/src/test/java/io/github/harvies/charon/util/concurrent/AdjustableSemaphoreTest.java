package io.github.harvies.charon.util.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
class AdjustableSemaphoreTest {

    @Test
    void setMaxPermits() throws InterruptedException {
        int task = 10;
        AdjustableSemaphore adjustableSemaphore = new AdjustableSemaphore(2);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CountDownLatch countDownLatch = new CountDownLatch(task);
        for (int i = 0; i < task; i++) {
            executorService.execute(() -> {
                try {
                    adjustableSemaphore.acquire();
                    log.warn("test");
                    TimeUnit.SECONDS.sleep(1);
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    adjustableSemaphore.release();
                }
            });
        }
        countDownLatch.await();

        log.warn("-----------------------");

        adjustableSemaphore.setMaxPermits(4);

        CountDownLatch countDownLatch2 = new CountDownLatch(task);
        for (int i = 0; i < task; i++) {
            executorService.execute(() -> {
                try {
                    adjustableSemaphore.acquire();
                    log.warn("test");
                    TimeUnit.SECONDS.sleep(1);
                    countDownLatch2.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    adjustableSemaphore.release();
                }
            });
        }
        countDownLatch2.await();

        log.warn("-----------------------");

        adjustableSemaphore.setMaxPermits(1);

        CountDownLatch countDownLatch3 = new CountDownLatch(task);
        for (int i = 0; i < task; i++) {
            executorService.execute(() -> {
                try {
                    adjustableSemaphore.acquire();
                    log.warn("test");
                    TimeUnit.SECONDS.sleep(1);
                    countDownLatch3.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    adjustableSemaphore.release();
                }
            });
        }
        countDownLatch3.await();
    }
}
