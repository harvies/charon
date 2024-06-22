package io.github.harvies.charon.util.concurrent.thread;

/**
 * @author harvies
 */
public class RunnableTest implements Runnable {

    @Override
    public void run() {
        System.err.println("running");
    }

    public static void main(String[] args) {
        new Thread(new RunnableTest()).start();
    }
}
