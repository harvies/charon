package io.github.harvies.charon.util.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * InheritableThread测试
 * 测试结果：
 * 在父线程中通过ThreadLocal设置值，子线程中获取不到
 * 在父线程中通过InheritableThreadLocal设置值，子线程中可以获取到
 * <p>
 * 实现原理：ThreadLocal中有一个 ThreadLocal.ThreadLocalMap类型的inheritableThreadLocals成员变量，存储父线程中设置的值
 * InheritableThreadLocal继承自ThreadLocal，重写了getMap()方法，返回该线程的inheritableThreadLocals成员变量
 */
@Slf4j
public class InheritableThreadLocalTest {

    public static final String PARENT_LOCAL_VALUE = "Parent Local";
    public static final String PARENT_INHERITABLE_VALUE = "Parent Inheritable";

    @Test
    public void test(){
        Thread parentThread = new Thread(() -> {
            ThreadLocal<String> local = new ThreadLocal<>();
            local.set(PARENT_LOCAL_VALUE);

            InheritableThreadLocal<String> inheritableLocal = new InheritableThreadLocal<>();
            inheritableLocal.set(PARENT_INHERITABLE_VALUE);

            new Thread(() -> {
                String localValue = local.get();
                log.info("Child Local: {}", localValue); // 输出 null
                Assertions.assertNull(localValue);
                String inheritableLocalValue = inheritableLocal.get();
                log.info("Child Inheritable: {}", inheritableLocalValue); // 输出 "Parent Inheritable"
                Assertions.assertEquals(PARENT_INHERITABLE_VALUE, inheritableLocalValue);
            }).start();
        });
        parentThread.start();
    }
}
