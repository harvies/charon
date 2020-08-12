package io.github.harvies.eris.base.jdk.concurrent.blockqueue;

import java.util.concurrent.SynchronousQueue;

/**
 * SynchronousQueue(不存储数据、可用于传递数据)
 * 是一个不存储元素的阻塞队列。每一个 put 操作必须等待一个 take 操作,否则不能继续添加元素。
 * SynchronousQueue 可以看成是一个传球手,负责把生产者线程处理的数据直接传递给消费者线
 * 程。队列本身并不存储任何元素,非常适合于传递性场景,比如在一个线程中使用的数据,传递给
 * 另 外 一 个 线 程 使 用 , SynchronousQueue 的 吞 吐 量 高 于 LinkedBlockingQueue 和
 * ArrayBlockingQueue。
 *
 * @author harvies
 */
public class SynchronousQueueTest {
    private static SynchronousQueue<Integer> queue = new SynchronousQueue<>();

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    queue.put(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    System.err.println(Thread.currentThread().getName() + "---" + queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();
    }
}
