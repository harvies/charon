package io.github.harvies.charon.agent;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

@Slf4j
public class TimeInterceptor {
    @RuntimeType
    public static Object intercept(@Origin Method method, @SuperCall Callable<?> callable)
            throws Exception {
        long start = System.currentTimeMillis();
        try {
//            log.info("stackTrace:[{}]", JsonUtils.toJSONString(Thread.currentThread().getStackTrace()));
            // 原有函数执行
            return callable.call();
        } finally {
            System.out.println(method + ": took " + (System.currentTimeMillis() - start) + "ms");
        }

    }
}