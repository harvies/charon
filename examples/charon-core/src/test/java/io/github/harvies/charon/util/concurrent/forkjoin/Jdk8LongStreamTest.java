package io.github.harvies.charon.util.concurrent.forkjoin;

import java.util.stream.LongStream;

/**
 * jdk1.8 LongStream计算
 *
 * @author harvies
 */
public class Jdk8LongStreamTest extends AbstractCompute {
    public static void main(String[] args) {
        new Jdk8LongStreamTest().main0();
    }

    @Override
    long sum(long start, long end) {
        return LongStream.rangeClosed(start, end)
                .parallel()
                .reduce(0, Long::sum);
    }
}

