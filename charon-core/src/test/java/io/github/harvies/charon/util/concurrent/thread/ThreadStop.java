package io.github.harvies.charon.util.concurrent.thread;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadStop {
    public static void main(String[] args) {
        Thread.currentThread().setName("定时任务线程");

        while (true) {
            log.info("新的定时任务开始执行");
            task();

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    private static void task() {
        Thread thread = new Thread(() -> {
            log.info("开始执行");

            ComputeThread computeThread = new ComputeThread(Thread.currentThread());
            computeThread.setName("计算线程");
            computeThread.start();
            ListenThread listenThread = new ListenThread(computeThread);
            listenThread.setName("监听线程");
            listenThread.setDaemon(true);
            listenThread.start();
            while (true) {
                log.info("执行中");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    log.warn("线程被中断,执行结束");
                    return;
                }
            }

        }, "执行计算任务线程");


        thread.start();
    }

    @Getter
    @Setter
    @RequiredArgsConstructor
    static
    class ListenThread extends Thread {
        @NonNull
        private Thread thread;

        @Override
        public void run() {
            while (true) {
                log.info("监听...");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                thread.interrupt();
                log.info("结束监听");
                break;
            }
        }
    }

    @Getter
    @Setter
    @RequiredArgsConstructor
    static
    class ComputeThread extends Thread {
        @NonNull
        private Thread thread;

        @Override
        public void run() {
            log.info("开始计算");

            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    thread.interrupt();
                    log.warn("有新数据，计算结束");
                    return;
                }
                log.info("处理:" + i);

            }

            log.info("计算完成");
        }
    }
}
