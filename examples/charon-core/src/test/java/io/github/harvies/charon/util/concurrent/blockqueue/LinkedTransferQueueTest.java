package io.github.harvies.charon.util.concurrent.blockqueue;

import java.util.concurrent.LinkedTransferQueue;

/**
 * LinkedTransferQueue
 * 是 一 个 由 链 表 结 构 组 成 的 无 界 阻 塞 TransferQueue 队 列 。 相 对 于 其 他 阻 塞 队 列 ,
 * LinkedTransferQueue 多了 tryTransfer 和 transfer 方法。
 * 1.transfer 方法:如果当前有消费者正在等待接收元素(消费者使用 take()方法或带时间限制的
 * poll()方法时),transfer 方法可以把生产者传入的元素立刻 transfer(传输)给消费者。如
 * 果没有消费者在等待接收元素,transfer 方法会将元素存放在队列的 tail 节点,并等到该元素
 * 被消费者消费了才返回。
 * 2.tryTransfer 方法。则是用来试探下生产者传入的元素是否能直接传给消费者。如果没有消费
 * 者等待接收元素,则返回 false。和 transfer 方法的区别是 tryTransfer 方法无论消费者是否
 * 接收,方法立即返回。而 transfer 方法是必须等到消费者消费了才返回。
 * 对于带有时间限制的 tryTransfer(E e, long timeout, TimeUnit unit)方法,则是试图把生产者传
 * 入的元素直接传给消费者,但是如果没有消费者消费该元素则等待指定的时间再返回,如果超时
 * 还没消费元素,则返回 false,如果在超时时间内消费了元素,则返回 true。
 *
 * @author harvies
 */
public class LinkedTransferQueueTest {
    private static  LinkedTransferQueue<Object> queue = new LinkedTransferQueue<>();
    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                boolean b = queue.tryTransfer(i);
                System.err.println(b);
                try {
                    queue.transfer(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
