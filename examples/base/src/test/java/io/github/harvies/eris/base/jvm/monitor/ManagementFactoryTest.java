package io.github.harvies.eris.base.jvm.monitor;

import org.junit.Test;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

public class ManagementFactoryTest {

    @Test
    public void testRuntimeMXBean() {
        //Java 虚拟机的运行时系统的管理接口。 RuntimeMXBean
        RuntimeMXBean run = ManagementFactory.getRuntimeMXBean();
        System.out.println("主机 " + run.getName());
        System.out.println("PID " + run.getPid());
        System.out.println("Java 虚拟机规范名称 " + run.getSpecName());
        System.out.println("返回 Java 库路径 " + run.getLibraryPath());
        System.out.println("系统类加载器用于搜索类文件的 Java 类路径 " + run.getClassPath());
        System.out.println("规范供应商 " + run.getSpecVendor());
        System.out.println("虚拟机规范版本 " + run.getSpecVersion());
        System.out.println("虚拟机参数 " + run.getInputArguments());
        System.out.println("虚拟机名称 " + run.getVmName());
        System.out.println("虚拟机版本 " + run.getVmVersion());
        System.out.println("虚拟机供应商 " + run.getVmVendor());

    }
}
