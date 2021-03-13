package io.github.harvies.charon.tests.base.jvm.monitor;

import java.lang.management.*;
import java.util.List;

public class JVM {

    public static void main(String[] args) {
        
        //Java 虚拟机线程系统的管理接口 ThreadMXBean
        ThreadMXBean th = ManagementFactory.getThreadMXBean();
        System.out.println("活动线程的当前数目" + th.getThreadCount());
        System.out.println("返回活动守护线程的当前数目" + th.getDaemonThreadCount());
        System.out.println("Java 虚拟机启动或峰值重置以来峰值活动线程计数" + th.getPeakThreadCount());
        System.out.println("返回当前线程的总 CPU 时间" + th.getCurrentThreadUserTime());
        System.out.println("当前线程在用户模式中执行的 CPU 时间" + th.getCurrentThreadUserTime());

        //用于操作系统的管理接口，Java 虚拟机在此操作系统上运行 OperatingSystemMXBean
        OperatingSystemMXBean op = ManagementFactory.getOperatingSystemMXBean();
        System.out.println("返回操作系统的架构" + op.getArch());
        System.out.println("返回操作系统名称" + op.getName());
        System.out.println("返回操作系统的版本" + op.getVersion());
        System.out.println("Java 虚拟机可以使用的处理器数目" + op.getAvailableProcessors());

        //内存池的管理接口。内存池表示由 Java 虚拟机管理的内存资源，
        //由一个或多个内存管理器对内存池进行管理 MemoryPoolMXBean
        List<MemoryPoolMXBean> list = ManagementFactory.getMemoryPoolMXBeans();
        for (MemoryPoolMXBean mem : list) {
            System.out.println("Java 虚拟机启动以来或自峰值重置以来此内存池的峰值内存使用量" + mem.getPeakUsage());
            System.out.println("返回此内存池的类型" + mem.getType());
            System.out.println("内存使用量超过其阈值的次数" + mem.getUsage());
        }
        //Java 虚拟机内存系统的管理接口。 MemoryMXBean
        MemoryMXBean mem = ManagementFactory.getMemoryMXBean();
        System.out.println(" 返回用于对象分配的堆的当前内存使用量" + mem.getHeapMemoryUsage());
        System.out.println("Java 虚拟机使用的非堆内存的当前内存使用量" + mem.getNonHeapMemoryUsage());
        // Java 虚拟机的编译系统的管理接口 CompilationMXBean
        CompilationMXBean com = ManagementFactory.getCompilationMXBean();
        System.out.println("即时 (JIT) 编译器的名称" + com.getName());
        System.out.println("在编译上花费的累积耗费时间的近似值(毫秒)" + com.getTotalCompilationTime());

        //Java 虚拟机的类加载系统的管理接口 ClassLoadingMXBean
        ClassLoadingMXBean cl = ManagementFactory.getClassLoadingMXBean();
        System.out.println("当前加载到 Java 虚拟机中的类的数量" + cl.getLoadedClassCount());
        System.out.println("Java 虚拟机开始执行到目前已经加载的类的总数" + cl.getTotalLoadedClassCount());
        System.out.println("Java 虚拟机开始执行到目前已经卸载的类的总数" + cl.getUnloadedClassCount());


    }

}