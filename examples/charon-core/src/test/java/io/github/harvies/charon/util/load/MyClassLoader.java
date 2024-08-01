package io.github.harvies.charon.util.load;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

/**
 * 类加载器双亲委派模型机制？
 * 当一个类收到了类加载请求时，不会自己先去加载这个类，而是将其委派给父类，由父类去加载，如果此时父类不能加载，反馈给子类，由子类去完成类的加载。
 *
 * @author harvies
 */
public class MyClassLoader extends ClassLoader {

    @Test
    public void test() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        Class<?> aaa = MyClassLoader.class.getDeclaredConstructor().newInstance().loadClass("myString");
        System.err.println("aaa:" + aaa.getName());
        Object bbb = aaa.getDeclaredConstructor().newInstance();
        System.err.println("bbb:" + bbb.getClass().getName());
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if ("myString".equals(name)) {
            /**
             * 加载String类
             */
            return super.loadClass("io.github.harvies.charon.util.load.test.String");
        }
        /**
         * 调用父加载器
         */
        return super.loadClass(name);
    }

}
