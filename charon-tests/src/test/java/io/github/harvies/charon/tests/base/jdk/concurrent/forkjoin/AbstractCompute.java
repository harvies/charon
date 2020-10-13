package io.github.harvies.charon.tests.base.jdk.concurrent.forkjoin;

import java.time.Duration;
import java.time.Instant;

/**
 * @author harvies
 */
public abstract class AbstractCompute {

    abstract long sum(long start, long end);

    public void main0() {
        Instant startInstant = Instant.now();
        long sum = sum(0, 10000000000L);
        System.out.printf("sum:%s,耗时:%d ms", sum, Duration.between(startInstant, Instant.now()).toMillis());
    }
}
