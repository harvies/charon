package io.github.harvies.charon.agent;

public class ClassC {

    public static void test3() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
