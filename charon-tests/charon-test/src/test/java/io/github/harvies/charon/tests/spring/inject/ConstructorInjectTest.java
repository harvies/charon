package io.github.harvies.charon.tests.spring.inject;

/**
 * 构造器注入
 *
 * @author harvies
 */
public class ConstructorInjectTest {
    private String message;

    /**
     * 带参数,方便利用构造器进行注入
     *
     * @param message
     */
    public ConstructorInjectTest(String message) {
        System.err.println("message:" + message);
        this.message = message;
    }
}
