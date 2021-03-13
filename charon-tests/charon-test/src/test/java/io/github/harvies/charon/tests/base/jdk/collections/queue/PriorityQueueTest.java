package io.github.harvies.charon.tests.base.jdk.collections.queue;

import org.junit.Test;

import java.util.HashMap;
import java.util.PriorityQueue;

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
        System.out.println("poll:" + priorityQueue.poll());

//           7
//         5   6   
//        1 4 2 3    
    }

    @Test
    public void testComparable() {
        PriorityQueue<HashMap> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new HashMap());
        priorityQueue.add(new HashMap());//java.lang.ClassCastException: java.util.HashMap cannot be cast to java.lang.Comparable
        System.out.println("array:" + priorityQueue);
        System.out.println("peek:" + priorityQueue.peek());
        System.out.println("poll:" + priorityQueue.poll());
        System.out.println("poll:" + priorityQueue.poll());
    }
}
