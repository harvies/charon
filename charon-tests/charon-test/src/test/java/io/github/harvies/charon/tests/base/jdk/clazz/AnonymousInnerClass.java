package io.github.harvies.charon.tests.base.jdk.clazz;

/**
 * 匿名内部类(要继承一个父类或者实现一个接口、直接使用
 * new 来生成一个对象的引用)
 * 匿名内部类我们必须要继承一个父类或者实现一个接口,当然也仅能只继承一个父类或者实现一
 * 个接口。同时它也是没有 class 关键字,这是因为匿名内部类是直接使用 new 来生成一个对象的引
 * 用。
 *
 * @author harvies
 */
public class AnonymousInnerClass {
    public void test(Bird bird) {
        System.out.println(bird.getName() + "能够飞 " + bird.fly() + "米");
    }

    public static void main(String[] args) {
        AnonymousInnerClass test = new AnonymousInnerClass();
        test.test(new Bird() {
            @Override
            public int fly() {
                return 10000;
            }

            @Override
            public String getName() {
                return "大雁";
            }
        });
    }
}

