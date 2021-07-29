package io.github.harvies.charon.tests.base.jvm.load;

public class AppClassLoaderTest {

    public static void main(String[] args) {
        System.out.println("app class load----------------------");
        final String s = System.getProperty("java.class.path");
        for (String s1 : s.split(":")) {
            System.out.println(s1);
        }
    }
}
