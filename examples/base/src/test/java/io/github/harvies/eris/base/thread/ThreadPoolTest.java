package io.github.harvies.eris.base.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author harvies
 */
@Slf4j
public class ThreadPoolTest {
    private static final ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Test
    public void submit() {
        log.warn("main start");
        Callable<Boolean> callable = () -> {
            Thread.sleep(1000);
            log.warn("thread exited");
            return true;
        };
        List<Future<Boolean>> futureList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Future<Boolean> future = executorService.submit(callable);
            futureList.add(future);
        }
        for (Future<Boolean> future :
                futureList) {
            try {
                future.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        log.warn("main exit");
    }

    @Test
    public void invokeAll() {
        log.warn("main start");
        List<Callable<Boolean>> callableList = new ArrayList<>();
        Callable<Boolean> callable = () -> {
            Thread.sleep(1000);
            log.warn("thread exited");
            return true;
        };
        for (int i = 0; i < 10; i++) {
            callableList.add(callable);
        }
        try {
            List<Future<Boolean>> futureList = executorService.invokeAll(callableList);
            for (Future<Boolean> future : futureList) {
                future.get();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.warn("main exit");
    }
}
