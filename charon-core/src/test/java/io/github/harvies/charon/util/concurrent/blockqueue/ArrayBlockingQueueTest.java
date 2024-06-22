package io.github.harvies.charon.util.concurrent.blockqueue;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;

/**
 * ArrayBlockingQueue(公平、非公平)
 * <p>
 * 用数组实现的有界阻塞队列。此队列按照先进先出(FIFO)的原则对元素进行排序。默认情况下
 * 不保证访问者公平的访问队列,所谓公平访问队列是指阻塞的所有生产者线程或消费者线程,当
 * 队列可用时,可以按照阻塞的先后顺序访问队列,即先阻塞的生产者线程,可以先往队列里插入
 * 元素,先阻塞的消费者线程,可以先从队列里获取元素。通常情况下为了保证公平性会降低吞吐
 * 量。我们可以使用以下代码创建一个公平的阻塞队列:
 *
 * @author harvies
 */
@Slf4j
public class ArrayBlockingQueueTest {
    /**
     * 默认非公平
     * 有界阻塞队列,必须传入数组大小
     */
    private static final ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(100);


    @Test
    public void test() throws InterruptedException {
        /**
         * put()加入(阻塞)
         */
        queue.put(1);
        /**
         * add()加入(非阻塞) 满了抛异常
         */
        queue.add(1);
        /**
         * offer()(非阻塞) 插入成功返回true
         */
        queue.offer(1);
        /**
         * 3:take() (阻塞):取走 BlockingQueue 里排在首位的对象,若 BlockingQueue 为空,阻断进入等待状
         * 态直到 BlockingQueue 有新的数据被加入。
         */
        queue.take();
        /**
         * 内部使用 ReentrantLock的Condition实现唤醒
         */
        /**
         * poll()取出(非阻塞),有超时时间参数
         */
        queue.poll();
        /**
         * drainTo()是BlockingQueue里的方法
         * 4.drainTo():一次性从 BlockingQueue 获取所有可用的数据对象(还可以指定获取数据的个
         * 数),通过该方法,可以提升获取数据效率;不需要多次分批加锁或释放锁。
         */
        ArrayList<Object> arrayList = new ArrayList<>();
        int i = queue.drainTo(arrayList, 5);
        log.info("实际取出{}个", i);
    }

}
