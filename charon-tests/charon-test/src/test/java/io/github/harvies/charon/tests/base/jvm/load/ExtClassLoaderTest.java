package io.github.harvies.charon.tests.base.jvm.load;

import java.io.File;
import java.util.StringTokenizer;

public class ExtClassLoaderTest {

    public static void main(String[] args) {
        System.out.println("ext class load----------------------");
        final String s = System.getProperty("java.ext.dirs");//对应路径
        System.out.println(s);
        System.out.println("-----------");

        File[] dirs;
        if (s != null) {
            StringTokenizer st =
                    new StringTokenizer(s, File.pathSeparator);
            int count = st.countTokens();
            dirs = new File[count];
            for (int i = 0; i < count; i++) {
                dirs[i] = new File(st.nextToken());
            }
        } else {
            dirs = new File[0];
        }

        for (File f : dirs) {
            System.out.println(f.getAbsolutePath());
        }
    }
}
