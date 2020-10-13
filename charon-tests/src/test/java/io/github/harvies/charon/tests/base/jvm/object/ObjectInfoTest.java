package io.github.harvies.charon.tests.base.jvm.object;

import io.github.harvies.charon.util.JsonUtils;
import lombok.Cleanup;
import org.apache.commons.io.IOUtils;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class ObjectInfoTest {
    public static void main(String[] args) throws IOException {
        @Cleanup InputStream resourceAsStream = ClassLoader.getSystemResourceAsStream("store.json");
        String storeStr = IOUtils.toString(Objects.requireNonNull(resourceAsStream), StandardCharsets.UTF_8);
        OSLocalStore obj = JsonUtils.parseObject(storeStr, OSLocalStore.class);
        //查看对象内部信息
        print(ClassLayout.parseInstance(obj).toPrintable());

        //查看对象外部信息
        print(GraphLayout.parseInstance(obj).toPrintable());

        //获取对象总大小
        print("size : " + GraphLayout.parseInstance(obj).totalSize());
    }

    static void print(String message) {
        System.out.println(message);
        System.out.println("-------------------------");
    }
}
