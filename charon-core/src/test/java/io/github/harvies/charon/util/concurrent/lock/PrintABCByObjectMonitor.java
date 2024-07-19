package io.github.harvies.charon.util.concurrent.lock;

public class PrintABCByObjectMonitor {

    private int state = 0;

    public static void main(String[] args) {
        PrintABCByObjectMonitor printABC = new PrintABCByObjectMonitor();
        new Thread(() -> {
            try {
                printABC.printA();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"ThreadA-").start();
        new Thread(() -> {
            try {
                printABC.printB();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"ThreadB-").start();
        new Thread(() -> {
            try {
                printABC.printC();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"ThreadC-").start();
    }


    private synchronized void printA() throws InterruptedException {
        for (; ; ) {
            if (state % 3 == 0) {
                System.out.println(Thread.currentThread().getName() +"A");
                state++;
                this.notifyAll();
            } else {
                this.wait();
            }
        }
    }

    private synchronized void printB() throws InterruptedException {
        for (; ; ) {
            if (state % 3 == 1) {
                System.out.println(Thread.currentThread().getName() +"B");
                state++;
                this.notifyAll();
            } else {
                this.wait();
            }
        }
    }

    private synchronized void printC() throws InterruptedException {
        for (; ; ) {
            if (state % 3 == 2) {
                System.out.println(Thread.currentThread().getName() + "C");
                state++;
                this.notifyAll();
            } else {
                this.wait();
            }
        }
    }

}
