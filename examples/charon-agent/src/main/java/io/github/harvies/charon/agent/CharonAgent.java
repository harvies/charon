package io.github.harvies.charon.agent;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CharonAgent {

    /**
     * 该方法在main方法之前运行，与main方法运行在同一个JVM中 并被同一个System ClassLoader装载
     * 被统一的安全策略(security policy)和上下文(context)管理
     *
     * @param agentOps agentOps
     * @param inst     inst
     */
    public static void premain(String agentOps, Instrumentation inst) {
        System.out.println("=========premain方法执行========");
//        System.out.println(agentOps);
//        // 添加Transformer
//        inst.addTransformer(new CharonTransformer());
//        System.out.println("=========premain方法执行完毕========");
        init();

        addInterceptor(inst);
    }

    private static void addInterceptor(Instrumentation inst) {
        installTimeInterceptor(inst);
    }

    private static void installTimeInterceptor(Instrumentation inst) {
        AgentBuilder.Transformer transformer = (builder, typeDescription, classLoader, module,protectionDomain) -> {
            return builder
                    .method(ElementMatchers.any()) // 拦截任意方法
                    .intercept(MethodDelegation.to(TimeInterceptor.class)); // 委托
        };

        AgentBuilder.Listener listener = new TimeListener();

        new AgentBuilder
                .Default()
//                .ignore(nameStartsWith("io.github.harvies.charon.util.JsonUtils."))
                .type(
                        ElementMatchers.nameStartsWith("io.github.harvies.charis")
                                .or(ElementMatchers.nameStartsWith("io.github.harvies.charon"))
                ) // 指定需要拦截的类
                .transform(transformer)
                .with(listener)
                .installOn(inst);
    }

    public static void init() {
        Executors.newScheduledThreadPool(1, new ThreadFactoryBuilder().setNameFormat("charon-agent-metric-%d").build()).scheduleAtFixedRate(() -> {
            Metric.printMemoryInfo();
            Metric.printGCInfo();
        }, 0, 30, TimeUnit.SECONDS);

    }

}
