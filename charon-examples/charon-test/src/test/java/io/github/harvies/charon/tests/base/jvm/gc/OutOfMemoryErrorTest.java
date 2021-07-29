package io.github.harvies.charon.tests.base.jvm.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author harvies
 * 堆内存溢出
 * VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 */
public class OutOfMemoryErrorTest {
    private static class OOMObject {

    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
        /**
         * 运行结果
         * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
         */
    }
}
