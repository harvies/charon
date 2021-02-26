package io.github.harvies.charon.util.concurrent;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 可调节许可大小的信号量
 */
public class AdjustableSemaphore {

    private final ResizeableSemaphore semaphore = new ResizeableSemaphore();

    private volatile int maxPermits = 0;

    public AdjustableSemaphore() {
    }

    public AdjustableSemaphore(int maxPermits) {
        if (maxPermits < 0) {
            throw new IllegalArgumentException("maxPermits must be a non-negative value");
        } else if (maxPermits > 0) {
            setMaxPermits(maxPermits);
        }
    }

    public int maxPermits() {
        return maxPermits;
    }

    public synchronized void setMaxPermits(int newMax) {
        if (newMax < 1) {
            throw new IllegalArgumentException("Semaphore size must be at least 1," + " was " + newMax);
        }

        int delta = newMax - this.maxPermits;

        if (delta == 0) {
            return;
        } else if (delta > 0) {
            // new max is higher, so release that many permits
            this.semaphore.release(delta);
        } else {
            delta = -delta;
            // delta < 0.
            // reducePermits needs a positive #, though.
            this.semaphore.reducePermits(delta);
        }

        this.maxPermits = newMax;
    }

    public void release() {
        this.semaphore.release();
    }

    public void acquire() throws InterruptedException {
        this.semaphore.acquire();
    }

    public boolean tryAcquire() {
        return this.semaphore.tryAcquire();
    }

    public boolean tryAcquire(int timeout, TimeUnit unit) throws InterruptedException {
        return this.semaphore.tryAcquire(timeout, unit);
    }

    public int availablePermits() {
        return this.semaphore.availablePermits();
    }

    private static final class ResizeableSemaphore extends Semaphore {

        private static final long serialVersionUID = 1L;

        ResizeableSemaphore() {
            super(0);
        }

        @Override
        protected void reducePermits(int reduction) {
            super.reducePermits(reduction);
        }
    }

}
