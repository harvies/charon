package io.github.harvies.charon.tests.base.jdk.concurrent.volatiletest;

/**
 * 我们使用javap命令查看字节码(javap -verbose InheritThreadClass.class)会发现在虚拟机中这个自增运算使用了4条指令(getstatic, iconst_1, iadd, putstatic)。 当getstatic指令把a的值压入栈顶时，volatile关键字保证了a的值此时是正确的，但是在执行iconst_1、iadd这些指令时其他线程有可能已经把a的值加大了，而已经在操作栈顶的值就变成了过期的数据了，所以putstatic指令执行后可能又把较小的a值同步回主内存了。 所以它不是一个原子运算，因此在多线程的情况下它并不是一个安全的操作。其实这么说也不是最严谨的，因为即使经过编译后的字节码只使用了一条指令进行运算也不代表这条指令就是原子操作。因为一条字节码指令在解释执行时，解释器需要运行许多行代码才能实现该条指令的语义，而即使是编译执行，一条字节码指令也可能需要转化成多条本地机器码指令。
 * <p>
 * 所以有关volatile的变量对其他线程的”可见性“的语义描述并不能得出这样的结论：基于volatile变量的运算在高并发下是安全的。
 * <p>
 * 那这种在高并发下的自增运算如何做到线程安全呢？可以使用synchronized，但是加锁的话性能开销太大，高并发下不是一个明智之选。可以使用并发包java.util.concurrent.atomic下的AtomicInteger原子类。
 * https://segmentfault.com/a/1190000008492598
 */
public class InheritThreadClass extends Thread {
    private static volatile int a = 0;

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            a++;
        }
    }

    public static void main(String[] args) {
        InheritThreadClass[] threads = new InheritThreadClass[100];
        for (int i = 0; i < 100; i++) {
            threads[i] = new InheritThreadClass();
            threads[i].start();
        }
        //等待所有子线程结束
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }

        //这段代码会在所有子线程运行完毕之后执行
        System.out.println(a);  //(1)
    }
}
