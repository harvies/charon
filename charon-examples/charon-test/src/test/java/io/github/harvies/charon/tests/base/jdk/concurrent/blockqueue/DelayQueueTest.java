package io.github.harvies.charon.tests.base.jdk.concurrent.blockqueue;

import lombok.Data;
import org.apache.commons.lang3.RandomUtils;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * DelayQueue(缓存失效、定时任务 )
 * <p>
 * 是一个支持延时获取元素的无界阻塞队列。队列使用 PriorityQueue 来实现。队列中的元素必须实
 * 现 Delayed 接口,在创建元素时可以指定多久才能从队列中获取当前元素。只有在延迟期满时才
 * 能从队列中提取元素。我们可以将 DelayQueue 运用在以下应用场景:
 * 1.
 * 缓存系统的设计:可以用 DelayQueue 保存缓存元素的有效期,使用一个线程循环查询
 * DelayQueue,一旦能从 DelayQueue 中获取元素时,表示缓存有效期到了。
 * 定 时 任 务 调 度 : 使 用 DelayQueue 保 存 当 天 将 会 执 行 的 任 务 和 执 行 时 间 , 一 旦 从
 * DelayQueue 中获取到任务就开始执行,从比如 TimerQueue 就是使用 DelayQueue 实现的。
 *
 * @author harvies
 */
public class DelayQueueTest {
    private static DelayQueue<DelayedTest> delayQueue = new DelayQueue<>();

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                DelayedTest delayedTest = new DelayedTest(RandomUtils.nextInt(1000, 10000));
                delayQueue.offer(delayedTest);
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                DelayedTest take = null;
                try {
                    take = delayQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.err.println("delayTime:" + take);
            }

        }).start();


    }
}

@Data
class DelayedTest implements Delayed {
    private long executeTime;
    private long delayTime;

    /**
     * delayTime本例子是毫秒
     *
     * @param delayTime
     */
    public DelayedTest(long delayTime) {
        this.executeTime = TimeUnit.NANOSECONDS.convert(delayTime, TimeUnit.MILLISECONDS) + System.nanoTime();
        this.delayTime = delayTime;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.executeTime - System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        DelayedTest delayedTest = (DelayedTest) o;
        if (delayedTest.executeTime == this.executeTime) {
            return 0;
        }
        return this.executeTime > delayedTest.executeTime ? 1 : -1;

    }
}
