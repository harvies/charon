package io.github.harvies.eris.base.jdk.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author harvies
 */
public class HelloCglibProxy implements MethodInterceptor {

    private Object target;

    public Object getInstance(Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        // 设置回调方法
        enhancer.setCallback(this);
        // 创建代理对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        //可针对目标方法进行增强(例如只拦截某个方法并增强，其他方法放行)
        System.out.println("目标方法:" + method.getName());
        System.out.println("方法执行前-cglib");
        Object result = methodProxy.invokeSuper(object, args);
        System.out.println("方法执行后-cglib");
        return result;
    }
}
