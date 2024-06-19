package io.github.harvies.charon.util.collection.queue;

import org.junit.Test;

import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * 优先队列(堆)
 * 默认：小顶堆（最小的元素在队列头部），可实现构造函数Comparator入参设置为大顶堆
 *
 * 常用API：
 * add：添加元素(调用offer()方法)
 * peek：获取队列头部元素，不删除
 * poll：获取队列头部元素，并删除
 *
 * 时间复杂度：插入和删除都是O(logN)，获取元素都是O(1)，空间复杂度：O(N)
 *
 * <p>
 */
public class PriorityQueueTest {
    @Test
    public void test() {
        //从大到小排序
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        priorityQueue.add(1);
        priorityQueue.add(5);
        priorityQueue.add(3);
        priorityQueue.add(4);
        priorityQueue.add(7);
        priorityQueue.add(2);
        priorityQueue.add(6);
        System.out.println("array:" + priorityQueue);
        System.out.println("peek:" + priorityQueue.peek());
        System.out.println("poll:" + priorityQueue.poll());
        System.out.println("array:" + priorityQueue);
        System.out.println("poll:" + priorityQueue.poll());
        System.out.println("array:" + priorityQueue);

//            7
//         5    6
//        1 4  2  3

//            6
//         5    3
//        1 4  2
    }

}
