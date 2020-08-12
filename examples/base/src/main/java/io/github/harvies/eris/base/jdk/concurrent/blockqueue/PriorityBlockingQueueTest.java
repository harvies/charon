package io.github.harvies.eris.base.jdk.concurrent.blockqueue;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * PriorityBlockingQueue(compareTo 排序实现优先)
 * 是 一 个 支持 优 先级 的 无界 队 列 。默 认 情况 下 元素 采 取 自然 顺 序升 序 排列 。 可 以自 定 义实 现
 * compareTo()方法来指定元素进行排序规则,或者初始化 PriorityBlockingQueue 时,指定构造
 * 参数 Comparator 来对元素进行排序。需要注意的是不能保证同优先级元素的顺序
 * <p>
 * 队列元素数组。平衡二叉堆实现
 *
 * @author harvies
 */
public class PriorityBlockingQueueTest {

    private static PriorityBlockingQueue<String> queue = new PriorityBlockingQueue<>();
    private static String[] strings = {"3", "6", "7", "1", "9", "8", "2", "5", "4", "0"};

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                queue.offer(strings[i]);
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
