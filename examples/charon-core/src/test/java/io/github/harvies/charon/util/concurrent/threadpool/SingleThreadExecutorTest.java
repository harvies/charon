package io.github.harvies.charon.util.concurrent.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Executors.newSingleThreadExecutor()返回一个线程池(这个线程池只有一个线程),这个线程
 * 池可以在线程死后(或发生异常时)重新启动一个线程来替代原来的线程继续执行下去!
 *
 * @author harvies
 */
public class SingleThreadExecutorTest {
    private static final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {
        /**
         * 建议修改线程名
         */

        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                System.err.println(Thread.currentThread().getName() + "---test");
            });
        }
    }
}
