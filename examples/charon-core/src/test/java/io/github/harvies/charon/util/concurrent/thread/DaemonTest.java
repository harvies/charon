package io.github.harvies.charon.util.concurrent.thread;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * Java分为两种线程：用户线程和守护线程
 * <p>
 * 所谓守护线程是指在程序运行的时候在后台提供一种通用服务的线程，比如垃圾回收线程就是一个很称职的守护者，并且这种线程并不属于程序中不可或缺的部分。因 此，当所有的非守护线程结束时，程序也就终止了，同时会杀死进程中的所有守护线程。反过来说，只要任何非守护线程还在运行，程序就不会终止。
 * <p>
 * 守护线程和用户线程的没啥本质的区别：唯一的不同之处就在于虚拟机的离开：如果用户线程已经全部退出运行了，只剩下守护线程存在了，虚拟机也就退出了。 因为没有了被守护者，守护线程也就没有工作可做了，也就没有继续运行程序的必要了。
 * <p>
 * 将线程转换为守护线程可以通过调用Thread对象的setDaemon(true)方法来实现。在使用守护线程时需要注意一下几点：
 * <p>
 * (1) thread.setDaemon(true)必须在thread.start()之前设置，否则会跑出一个IllegalThreadStateException异常。你不能把正在运行的常规线程设置为守护线程。
 * <p>
 * (2) 在Daemon线程中产生的新线程也是Daemon的。
 * <p>
 * (3) 守护线程应该永远不去访问固有资源，如文件、数据库，因为它会在任何时候甚至在一个操作的中间发生中断。
 *
 * @author harvies
 */
public class DaemonTest {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    System.err.println(Thread.currentThread().getName() + " running " + DateFormatUtils.format(System.currentTimeMillis(), DateFormatUtils.ISO_8601_EXTENDED_DATETIME_FORMAT.getPattern()));
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
}
