package io.github.harvies.charon.util.concurrent.thread;

/**
 * yiled()方法的作用是放弃当前CPU的资源，将资源让给其它线程，但放弃的时间不确定，有可能刚刚放弃，又马上获得了CPU时间片
 *
 * @author harvies
 */
public class YieldTest {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.err.println("thread runing");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setName("Thread-test");
        thread.start();
        Thread.yield();
        System.err.println("main thread end");
    }
}
