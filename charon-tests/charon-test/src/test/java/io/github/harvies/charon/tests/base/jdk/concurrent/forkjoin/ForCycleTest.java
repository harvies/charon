package io.github.harvies.charon.tests.base.jdk.concurrent.forkjoin;

/**
 * for循环计算
 *
 * @author harvies
 */
public class ForCycleTest extends AbstractCompute {
    public static void main(String[] args) {
        new ForCycleTest().main0();
    }

    @Override
    long sum(long start, long end) {
        long sum = 0;
        for (long i = start; i <= end; i++) {
            sum += i;
        }
        return sum;
    }
}

