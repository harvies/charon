package io.github.harvies.charon.agent;

public class ClassB {

    public static void test2() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
