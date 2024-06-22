package io.github.harvies.charon.util.jvm.gc;

/**
 * @author harvies
 * 虚拟机栈和本地方法栈OOM测试
 * <p>
 */
public class StackOverflowErrorTest {
    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        StackOverflowErrorTest stackOverflowErrorTest = new StackOverflowErrorTest();
        try {
            stackOverflowErrorTest.stackLeak();
        } catch (Throwable t) {
            System.err.println("stack length:" + stackOverflowErrorTest.stackLength);
            throw t;
        }
        /**
         * 运行结果 stack length:16637
         *  * Exception in thread "main" java.lang.StackOverflowError
         */
    }
}
