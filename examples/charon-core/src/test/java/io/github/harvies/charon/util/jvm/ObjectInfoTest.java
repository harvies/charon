package io.github.harvies.charon.util.jvm;

import lombok.Cleanup;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

import java.io.IOException;
import java.io.InputStream;

public class ObjectInfoTest {
    public static void main(String[] args) throws IOException {
        @Cleanup InputStream resourceAsStream = ClassLoader.getSystemResourceAsStream("store.json");
        Integer a =1;
        //查看对象内部信息
        print(ClassLayout.parseInstance(a).toPrintable());

        //查看对象外部信息
        print(GraphLayout.parseInstance(a).toPrintable());

        //获取对象总大小
        print("size : " + GraphLayout.parseInstance(a).totalSize());
    }

    static void print(String message) {
        System.out.println(message);
        System.out.println("-------------------------");
    }
}
