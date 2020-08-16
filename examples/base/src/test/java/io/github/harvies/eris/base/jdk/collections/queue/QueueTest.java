package io.github.harvies.eris.base.jdk.collections.queue;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class QueueTest {
    @Test
    public void test1() throws InterruptedException {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
        for (int i = 0; i < 10; i++) {
            queue.offer(i);
        }
        for (int i = 0; i < 10; i++) {
            Integer take = queue.take();
            System.err.println(take);
        }
    }
}