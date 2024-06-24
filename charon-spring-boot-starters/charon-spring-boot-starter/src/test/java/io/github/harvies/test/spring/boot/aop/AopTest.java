package io.github.harvies.test.spring.boot.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author harvies
 */
@Aspect
@Component
@Slf4j
public class AopTest {
    @Pointcut(value = "execution(* io.github.harvies.test.spring.boot.BeanA.methodA(..))")
    public void point() {
    }

    @Before(value = "point()")
    public void before() {
        log.info("transaction begin");
    }

    @AfterReturning(value = "point()")
    public void after() {
        log.info("transaction commit");
    }

    @Around("point()")
    public boolean around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("around transaction begin");
        boolean proceed = (boolean) joinPoint.proceed();
        log.info("around transaction commit");
        return proceed;
    }
}
