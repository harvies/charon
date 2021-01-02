package io.github.harvies.charon.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;

class CharonConfigTest extends BaseTest {

    @Resource
    private Environment environment;

    @Value("${aaa}")
    private String aaa;

    @Test
    void test() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            System.out.println(environment.getProperty("aaa"));
            System.out.println(aaa);
            Thread.sleep(1000);
            System.out.println("--------");
        }
    }
}
