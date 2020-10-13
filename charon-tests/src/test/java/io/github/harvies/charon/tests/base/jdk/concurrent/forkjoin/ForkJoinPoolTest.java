package io.github.harvies.charon.tests.base.jdk.concurrent.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author harvies
 */
public class ForkJoinPoolTest extends AbstractCompute {
    public static void main(String[] args) {
        new ForkJoinPoolTest().main0();
    }

    @Override
    long sum(long start, long end) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> forkJoinTask = new ForkJoinSumCalculate(start, end);
        return forkJoinPool.invoke(forkJoinTask);
    }
}

class ForkJoinSumCalculate extends RecursiveTask<Long> {

    private long start;
    private long end;

    public ForkJoinSumCalculate(long start, long end) {
        this.start = start;
        this.end = end;
    }

    /**
     * 临界值
     */
    private static final long THRESHOLD = 10000L;

    @Override
    protected Long compute() {
        long length = end - start;
        if (length <= THRESHOLD) {
            long sum = 0;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            long middle = (start + end) / 2;
            ForkJoinSumCalculate left = new ForkJoinSumCalculate(start, middle);
            /**
             * 进行拆分,同事压入线程队列
             */
            left.fork();
            ForkJoinSumCalculate right = new ForkJoinSumCalculate(middle + 1, end);
            right.fork();
            return left.join() + right.join();
        }
    }
}
