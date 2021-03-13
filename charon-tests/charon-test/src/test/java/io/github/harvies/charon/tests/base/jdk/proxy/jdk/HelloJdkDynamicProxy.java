package io.github.harvies.charon.tests.base.jdk.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author harvies
 */
public class HelloJdkDynamicProxy implements InvocationHandler {
    Object originalObj;

    Object bind(Object originalObj) {
        this.originalObj = originalObj;
        return Proxy.newProxyInstance(originalObj.getClass().getClassLoader(), originalObj.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //可针对目标方法进行增强(例如只拦截某个方法并增强，其他方法放行)
        System.out.println("目标方法:" + method.getName());
        System.out.println("方法执行前-jdkDynamicProxy");
        Object result = method.invoke(originalObj, args);
        System.out.println("方法执行后-jdkDynamicProxy");
        return result;
    }
}
