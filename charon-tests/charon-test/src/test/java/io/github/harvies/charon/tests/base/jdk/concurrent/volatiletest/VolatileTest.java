package io.github.harvies.charon.tests.base.jdk.concurrent.volatiletest;


/**
 * 1、保证被volatile修饰的变量对所有其他的线程的可见性,轻量级的同步策略。
 * 2、使用volatile修饰的变量禁止指令重排优化。
 * <p>
 * 缺点:
 * 1.不具备"互斥性"
 * 2.不能保证变量的"原子性"
 *
 * @author harvies
 */
public class VolatileTest {
    public static void main(String[] args) throws InterruptedException {
        TestThread thread = new TestThread();
        new Thread(thread).start();
        Thread.sleep(10);
        thread.setFlag(false);
    }
}

class TestThread implements Runnable {

    public void setFlag(boolean flag) {
        this.flag = flag;
    }


    //    private boolean flag = true;
    private volatile boolean flag = true;

    @Override
    public void run() {
        while (flag) {

        }
        //如果不用volatile修饰,end永远都不会打印,线程还没来得及从主存读取数据(如果sleep,则会停止)
        System.err.println("end");
    }
}
