package io.github.harvies.charon.util.concurrent;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TtlExecutorServiceTest {
    private static final Logger log = LoggerFactory.getLogger(TtlExecutorServiceTest.class);

    @Test
    public void test1() throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();

        // Wrapping ExecutorService with TTL
        ExecutorService ttlExecutorService = TtlExecutors.getTtlExecutorService(executorService);

        TransmittableThreadLocal<String> context = new TransmittableThreadLocal<>();

        context.set("value-set-in-parent");
        System.out.println("[parent thread] set " + context.get());

        /////////////////////////////////////
        // Runnable
        /////////////////////////////////////
        Runnable task = () -> System.out.println("[child thread] get " + context.get() + " in Runnable");
        ttlExecutorService.submit(task).get();

        /////////////////////////////////////
        // Callable
        /////////////////////////////////////
        Callable<Integer> call = () -> {
            System.out.println("[child thread] get " + context.get() + " in Callable");
            return 42;
        };
        ttlExecutorService.submit(call).get();

        /////////////////////////////////////
        // Cleanup
        /////////////////////////////////////
        ttlExecutorService.shutdown();
    }

    @Test
    public void test2() throws ExecutionException, InterruptedException {

        // Wrapping ExecutorService with TTL
        ExecutorService ttlExecutorService = Executors.newCachedThreadPool();

        InheritableThreadLocal<String> context = new InheritableThreadLocal<>();

        context.set("value-set-in-parent");
        System.out.println("[parent thread] set " + context.get());

        /////////////////////////////////////
        // Runnable
        /////////////////////////////////////
        Runnable task = () -> System.out.println("[child thread] get " + context.get() + " in Runnable");
        ttlExecutorService.submit(task).get();

        /////////////////////////////////////
        // Callable
        /////////////////////////////////////
        Callable<Integer> call = () -> {
            System.out.println("[child thread] get " + context.get() + " in Callable");
            return 42;
        };
        ttlExecutorService.submit(call).get();

        /////////////////////////////////////
        // Cleanup
        /////////////////////////////////////
        ttlExecutorService.shutdown();
    }
}
