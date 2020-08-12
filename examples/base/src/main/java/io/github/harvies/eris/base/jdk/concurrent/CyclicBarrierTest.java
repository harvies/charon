package io.github.harvies.eris.base.jdk.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier（回环栅栏-等待至 barrier 状态再全部同时执
 *
 * 假若有若干个线程都要进行写数据操作，并且只有所有线程都完成写数据操作之后，这些线程才能继续做后面的事情，此时就可以利用CyclicBarrier了
 *
 * 字面意思回环栅栏，通过它可以实现让一组线程等待至某个状态之后再全部同时执行。叫做回环
 * 是因为当所有等待线程都被释放以后，CyclicBarrier 可以被重用。我们暂且把这个状态就叫做
 * barrier，当调用 await()方法之后，线程就处于 barrier 了。
 * CyclicBarrier 中最重要的方法就是 await 方法，它有 2 个重载版本：
 * 1. public int await()：用来挂起当前线程，直至所有线程都到达 barrier 状态再同时执行后续任
 * 务；
 * 2. public int await(long timeout, TimeUnit unit)：让这些线程等待至一定的时间，如果还有
 * 线程没有到达 barrier 状态就直接让到达 barrier 的线程执行后续任务。
 * 具体使用如下，另外 CyclicBarrier 是可以重用的。
 *
 * 出处:https://www.cnblogs.com/dolphin0520/p/3920397.html
 *
 * @author harvies
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        int n = 4;
        /**
         * 如果说想在所有线程写入操作完之后，进行额外的其他操作可以为CyclicBarrier提供Runnable参数：
         */
        CyclicBarrier barrier = new CyclicBarrier(n);
        for (int i = 0; i < n; i++) {
            new Writer(barrier).start();
        }
    }

    static class Writer extends Thread {
        private CyclicBarrier cyclicBarrier;

        public Writer(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("线程" + Thread.currentThread().getName() + "正在写入数据...");
            try {
                Thread.sleep(5000);      //以睡眠来模拟写入数据操作
                System.out.println("线程" + Thread.currentThread().getName() + "写入数据完毕，等待其他线程写入完毕");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("所有线程写入完毕，继续处理其他任务...");
        }
    }
}
