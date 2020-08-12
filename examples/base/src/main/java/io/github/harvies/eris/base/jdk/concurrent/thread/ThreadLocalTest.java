package io.github.harvies.eris.base.jdk.concurrent.thread;


/**
 * ThreadLocal 作用（线程本地存储）
 * <p>
 * ThreadLocal，很多地方叫做线程本地变量，也有些地方叫做线程本地存储，ThreadLocal 的作用
 * 是提供线程内的局部变量，这种变量在线程的生命周期内起作用，减少同一个线程内多个函数或
 * 者组件之间一些公共变量的传递的复杂度。
 * <p>
 * ThreadLocalMap（线程的一个属性）
 * 1. 每个线程中都有一个自己的 ThreadLocalMap 类对象，可以将线程自己的对象保持到其中，
 * 各管各的，线程可以正确的访问到自己的对象。
 * 2. 将一个共用的 ThreadLocal 静态实例作为 key，将不同对象的引用保存到不同线程的
 * ThreadLocalMap 中，然后在线程执行的各处通过这个静态 ThreadLocal 实例的 get()方法取
 * 得自己线程保存的那个对象，避免了将这个对象作为参数传递的麻烦。
 * 3. ThreadLocalMap 其实就是线程里面的一个属性，它在 Thread 类中定义
 * ThreadLocal.ThreadLocalMap threadLocals = null;
 * 使用场景
 * 最常见的 ThreadLocal 使用场景为 用来解决 数据库连接、Session 管理等。
 *
 * @author harvies
 */
public class ThreadLocalTest {
    private static final ThreadLocal threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                System.err.println(Thread.currentThread().getName() + " set:" + finalI);
                /**
                 * 看源码一下就懂了
                 */
                threadLocal.set(finalI);
                System.err.println(Thread.currentThread().getName() + " get:" + threadLocal.get());
                threadLocal.remove();
            }, "thread" + i).start();
        }
    }

}
