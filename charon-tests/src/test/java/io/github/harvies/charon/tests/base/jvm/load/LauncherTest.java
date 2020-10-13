package io.github.harvies.charon.tests.base.jvm.load;

import sun.misc.Launcher;

public class LauncherTest {
    public static void main(String[] args) {
        ClassLoader classLoader = Launcher.class.getClassLoader();
        System.out.println(classLoader); //null
    }
}