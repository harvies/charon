package io.github.harvies.charon.tests.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author harvies
 */
@Aspect
@Component
public class TransactionTest {
    @Pointcut(value = "execution(* io.github.harvies.charon.tests.spring.service.LoginService.login(..))")
    public void point() {
    }

    @Before(value = "point()")
    public void before() {
        System.out.println("transaction begin");
    }

    @AfterReturning(value = "point()")
    public void after() {
        System.out.println("transaction commit");
    }

    @Around("point()")
    public boolean around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("transaction begin");
        boolean proceed = (boolean) joinPoint.proceed();
        System.out.println("transaction commit");
        return proceed;
    }
}
