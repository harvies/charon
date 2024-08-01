package io.github.harvies.charon.util.concurrent.blockqueue;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * LinkedBlockingDeque
 * 是一个由链表结构组成的双向阻塞队列。所谓双向队列指的你可以从队列的两端插入和移出元素。
 * 双端队列因为多了一个操作队列的入口,在多线程同时入队时,也就减少了一半的竞争。相比其
 * 他 的 阻 塞 队 列 , LinkedBlockingDeque 多 了 addFirst , addLast , offerFirst , offerLast ,
 * peekFirst,peekLast 等方法,以 First 单词结尾的方法,表示插入,获取(peek)或移除双端队
 * 列的第一个元素。以 Last 单词结尾的方法,表示插入,获取或移除双端队列的最后一个元素。另
 * 外插入方法 add 等同于 addLast,移除方法 remove 等效于 removeFirst。但是 take 方法却等同
 * 于 takeFirst,不知道是不是 Jdk 的 bug,使用时还是用带有 First 和 Last 后缀的方法更清楚。
 * 在初始化 LinkedBlockingDeque 时可以设置容量防止其过渡膨胀。另外双向阻塞队列可以运用在
 * “工作窃取”模式中。
 *
 * @author harvies
 */
public class LinkedBlockingDequeTest {
    private static LinkedBlockingDeque<String> deque = new LinkedBlockingDeque<>();

    @Test
    public void addFirst() {
        deque.addFirst("1");
        System.err.println(deque);
    }

    @Test
    public void addLast() {
        deque.addLast("1");
        System.err.println(deque);
    }

    @Test
    public void removeFirst() {
        deque.addFirst("1");
        Object o = deque.removeFirst();
        System.err.println(o);
    }

    @Test
    public void removeLast() {
        deque.addFirst("1");
        Object o = deque.removeLast();
        System.err.println(o);
    }
}
