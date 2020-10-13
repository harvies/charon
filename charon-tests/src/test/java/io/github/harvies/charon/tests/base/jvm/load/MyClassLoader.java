package io.github.harvies.charon.tests.base.jvm.load;

/**
 * 类加载器双亲委派模型机制？
 * 当一个类收到了类加载请求时，不会自己先去加载这个类，而是将其委派给父类，由父类去加载，如果此时父类不能加载，反馈给子类，由子类去完成类的加载。
 *
 * @author harvies
 */
public class MyClassLoader extends ClassLoader {
    @Override
    public Class<?> loadClass(java.lang.String name) throws ClassNotFoundException {
        if ("myClassName".equals(name)) {
            /**
             * 加载String类
             */
            return super.loadClass("io.github.harvies.eris.base.jvm.load.test.String");
        }
        /**
         * 调用父加载器
         */
        return super.loadClass(name);
    }

    public static void main(java.lang.String[] args) {
        try {
            Class<?> aaa = MyClassLoader.class.getDeclaredConstructor().newInstance().loadClass("myClassName");
            System.err.println("aaa:" + aaa);
            Object aaa1 = aaa.getDeclaredConstructor().newInstance();
            System.err.println("aaa1:" + aaa1.getClass().getName());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
