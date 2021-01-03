package io.github.harvies.charon.config;

import io.github.harvies.charon.config.annotation.RefreshScope;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;

@RefreshScope
class CharonConfigTest extends BaseTest {

    @Resource
    private Environment environment;

    @Value("${aaa}")
    private String aaa;

    @Value("${aaa}")
    private String bbb;

    @Resource
    private Config config;

    @Test
    void test() throws InterruptedException {
        int size = 10;
        CountDownLatch countDownLatch = new CountDownLatch(size);

        for (int i = 0; i < 10; i++) {
            System.out.println(environment.getProperty("aaa"));
            System.out.println(aaa);
            System.out.println(bbb);
            System.out.println(config.getBbb());
            Thread.sleep(3000);
            System.out.println("--------");
        }
        countDownLatch.await();
    }
}
