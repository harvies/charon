package io.github.harvies.charon.util.jvm.gc;

/**
 * @author harvies
 * 创建线程导致内存溢出
 */
public class StackOOMTest {
    private int threadNum = 0;

    private void dontStop() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stackLeakByThread() {
        while (true) {
            Thread thread = new Thread(() -> dontStop());
            thread.start();
            threadNum++;
        }
    }

    public static void main(String[] args) {
        StackOOMTest stackOOMTest = new StackOOMTest();
        try {
            stackOOMTest.stackLeakByThread();
        } catch (Throwable t) {
            System.err.println("thread num:" + stackOOMTest.threadNum);
            throw t;
        }
        /**
         * 运行结果
         * thread num:9683
         * Exception in thread "main" java.lang.OutOfMemoryError: unable to create new native thread
         */
    }
}
