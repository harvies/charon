package io.github.harvies.charon.tests.base.jdk.concurrent.thread;

public class ThreadExitSuccess {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.err.println("aaa");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.err.println("bbb");
        });
        thread.start();
        Thread.sleep(1000);
        while (!thread.isInterrupted()) {
            thread.interrupt();
            Thread.sleep(1000);
        }
    }
}
