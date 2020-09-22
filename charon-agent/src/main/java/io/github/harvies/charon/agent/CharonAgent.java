package io.github.harvies.charon.agent;

import java.lang.instrument.Instrumentation;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CharonAgent {
    /**
     * 该方法在main方法之前运行，与main方法运行在同一个JVM中 并被同一个System ClassLoader装载
     * 被统一的安全策略(security policy)和上下文(context)管理
     */
    public static void premain(String agentOps, Instrumentation inst) {
        System.out.println("=========premain方法执行========");
//        System.out.println(agentOps);
//        // 添加Transformer
//        inst.addTransformer(new CharonTransformer());
//        System.out.println("=========premain方法执行完毕========");
        init();
    }

    public static void init() {
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(() -> {
            Metric.printMemoryInfo();
            Metric.printGCInfo();
        }, 0, 5000, TimeUnit.MILLISECONDS);
    }
}
