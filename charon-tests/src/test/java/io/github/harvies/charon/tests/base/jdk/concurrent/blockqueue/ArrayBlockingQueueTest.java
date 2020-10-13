package io.github.harvies.charon.tests.base.jdk.concurrent.blockqueue;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

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
public class ArrayBlockingQueueTest {
    /**
     * 默认非公平
     * 有界阻塞队列,必须传入数组大小
     */
    private static final ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(100);

    public static void main(String[] args) {

        new Thread(() -> {
            for (int j = 0; j < 10; j++) {
                try {
                    /**
                     * put()加入(阻塞)
                     */
                    queue.put(j);
                    /**
                     * add()加入(非阻塞) 满了抛异常
                     */
//                    queue.add(j);
                    /**
                     * offer()(非阻塞) 插入成功返回true
                     */
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (int j = 0; j < 10; j++) {
                try {
                    /**
                     * 3:take():取走 BlockingQueue 里排在首位的对象,若 BlockingQueue 为空,阻断进入等待状
                     * 态直到 BlockingQueue 有新的数据被加入。
                     *
                     * 内部使用 ReentrantLock的Condition实现唤醒
                     */
                    /**
                     * poll()取出(非阻塞),有超时时间参数
                     */
                    System.err.println(Thread.currentThread().getName() + "---" + queue.take());
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (int j = 0; j < 10; j++) {
                try {
                    ArrayList<Integer> arrayList = new ArrayList<>();
                    /**
                     * drainTo()是BlockingQueue里的方法
                     * 4.drainTo():一次性从 BlockingQueue 获取所有可用的数据对象(还可以指定获取数据的个
                     * 数),通过该方法,可以提升获取数据效率;不需要多次分批加锁或释放锁。
                     */
                    int i = queue.drainTo(arrayList, 5);
                    System.err.println("实际取出" + i + "个");
                    System.err.println(Thread.currentThread().getName() + "---" + arrayList);
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
