package io.github.harvies.charon.util.concurrent.blockqueue;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author harvies
 */
public class LinkedBlockingQueueTest {
    /**
     * LinkedBlockingQueue(两个独立锁提高并发)
     * <p>
     * 基于链表的阻塞队列,同 ArrayListBlockingQueue 类似,此队列按照先进先出(FIFO)的原则对
     * 元素进行排序。而 LinkedBlockingQueue 之所以能够高效的处理并发数据,还因为其对于生产者
     * 端和消费者端分别采用了独立的锁来控制数据同步,这也意味着在高并发的情况下生产者和消费
     * 者可以并行地操作队列中的数据,以此来提高整个队列的并发性能。
     * <p>
     * LinkedBlockingQueue 会默认一个类似无限大小的容量(Integer.MAX_VALUE)。
     */
    private static LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                queue.offer(i);
            }

        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    System.err.println(Thread.currentThread().getName() + "-" + queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();
    }
}
