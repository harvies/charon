package io.github.harvies.charon.agent;

public class ClassA {

    public static void test1() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ClassC.test3();
    }
}
