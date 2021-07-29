package io.github.harvies.charon.tests.base.jdk.concurrent.thread;

/**
 * @author harvies
 */
public class ThreadTest extends Thread {
    @Override
    public void run() {
        System.err.println("running");
    }

    public static void main(String[] args) {
        new ThreadTest().start();
    }
}
