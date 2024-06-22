package io.github.harvies.charon.util.concurrent.blockqueue;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

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
@Slf4j
public class DelayQueueTest {
    private static DelayQueue<TestDelayed> delayQueue = new DelayQueue<>();

    @Test
    public void test() throws ExecutionException, InterruptedException {
        log.info("begin");
        CompletableFuture<Void> completableFuture1 = CompletableFuture.runAsync(() -> {
            for (int i = 0; i < 10; i++) {
                TestDelayed testDelayed = new TestDelayed(RandomUtils.nextInt(1000, 10000));
                delayQueue.offer(testDelayed);
            }
        });
        CompletableFuture<Void> completableFuture2 = CompletableFuture.runAsync(() -> {
            for (int i = 0; i < 10; i++) {
                TestDelayed take = null;
                try {
                    take = delayQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("delayTime:{}", take);
            }
        });
        CompletableFuture<Void> completableFuture = CompletableFuture.allOf(completableFuture1, completableFuture2);
        completableFuture.get();
        log.info("end");
    }
}

@Data
class TestDelayed implements Delayed {
    private long executeTime;
    private long delayTime;

    /**
     * delayTime本例子是毫秒
     *
     * @param delayTime
     */
    public TestDelayed(long delayTime) {
        this.executeTime = TimeUnit.NANOSECONDS.convert(delayTime, TimeUnit.MILLISECONDS) + System.nanoTime();
        this.delayTime = delayTime;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.executeTime - System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        TestDelayed testDelayed = (TestDelayed) o;
        if (testDelayed.executeTime == this.executeTime) {
            return 0;
        }
        return this.executeTime > testDelayed.executeTime ? 1 : -1;

    }
}
