package io.github.harvies.charon.util.concurrent.threadpool;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExecutorTest {
    private static final ScheduledThreadPoolExecutor scheduledThreadPool = new ScheduledThreadPoolExecutor(10);

    public static void main(String[] args) {
        scheduledThreadPool.schedule(() -> System.out.println("延迟三秒"), 3, TimeUnit.SECONDS);

        scheduledThreadPool.scheduleAtFixedRate(() -> System.out.println("延迟 1 秒后每三秒执行一次"), 1, 3, TimeUnit.SECONDS);
    }
}
