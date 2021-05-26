package io.github.harvies.charon.agent;

import org.junit.jupiter.api.Test;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

import java.util.ArrayList;

public class JolTest {

    @Test
    void test() {
        Object obj = generate();

        //查看对象内部信息
        print(ClassLayout.parseInstance(obj).toPrintable());

        //查看对象外部信息
        print(GraphLayout.parseInstance(obj).toPrintable());

        //获取对象总大小
        print("size : " + GraphLayout.parseInstance(obj).totalSize());
    }

    static Object generate() {
        ArrayList<Object> arrayList = new ArrayList<>();
        arrayList.add("aa");
        return arrayList;
    }

    static void print(String message) {
        System.out.println(message);
        System.out.println("-------------------------");
    }


}