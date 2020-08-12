package io.github.harvies.eris.base.jdk.concurrent.thread;

/**
 * 多线程卖票
 *
 * @author harvies
 */
public class TicketConsume implements Runnable {
    private static Integer ticketNum = 10;//拆箱导致对象变了　不能锁
    private static Object lock = new Object();//一把锁

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            synchronized (lock) {
                if (ticketNum > 0) {
                    System.err.println("线程" + Thread.currentThread().getName() + "正在卖出第" + ticketNum + "张票");
                    ticketNum--;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new TicketConsume()).start();
        }
    }
}
