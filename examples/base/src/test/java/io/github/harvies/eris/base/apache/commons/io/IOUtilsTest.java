package io.github.harvies.eris.base.apache.commons.io;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class IOUtilsTest {
    @Test
    public void test() {
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.txt");

        List<String> strings = null;
        try {
            strings = IOUtils.readLines(resourceAsStream, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                resourceAsStream.close();//在finally里关闭流
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.err.println(strings);
    }
}
