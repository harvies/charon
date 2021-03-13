package io.github.harvies.charon.tests.base.apache.commons.io;

import io.github.harvies.charon.util.FileUtils;
import lombok.Cleanup;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.junit.Test;

import java.io.File;
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

    @Test
    public void test2() throws IOException {
        @Cleanup LineIterator lineIterator = FileUtils.lineIterator(new File(FileUtils.getCurrentUserHomePath() + "/111"));
        while (lineIterator.hasNext()) {
            String next = lineIterator.next();
            System.out.println(next);
        }
    }
}
