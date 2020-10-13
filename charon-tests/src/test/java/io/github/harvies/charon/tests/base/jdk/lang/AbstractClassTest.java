package io.github.harvies.charon.tests.base.jdk.lang;

/**
 * 抽象类测试
 */
public abstract class AbstractClassTest {
    /**
     * 1.抽象类中可以定义构造器
     */
    public AbstractClassTest() {
    }

    /**
     * 2.可以有抽象方法和具体方法
     */

    public void specificVoid() {

    }

    /**
     * 3.接口中的成员全都是 public 的
     */
    /**
     * 11. 抽象的(abstract)方法是否可同时是静态的(static), 是否可同时是本地方法
     * (native)，是否可同时被 synchronized（2017-11-16-wl）
     * 都不能。抽象方法需要子类重写，而静态的方法是无法被重写的，因此二者是矛盾的。本地方法是由
     * 本地代码（如 C 代码）实现的方法，而抽象方法是没有实现的，也是矛盾的。synchronized 和方法的实现细节有关，
     * 抽象方法不涉及实现细节，因此也是相互矛盾的。
     */
    protected abstract void abstractVoid();

    /**
     * 4.抽象类中可以定义成员变量
     */
    private Integer a;

    /**
     * 6.抽象类中可以包含静态
     */
    public static void staticVoid() {

    }

}
