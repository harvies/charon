package io.github.harvies.charon.config;

import io.github.harvies.charon.config.annotation.RefreshScope;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;

@Slf4j
@RefreshScope
public class CharonConfigTest extends BaseTest {

    @Resource
    private Environment environment;

    @Value("${aaa}")
    private String aaa;

    @Value("${aaa}")
    private String bbb;

    @Resource
    private Config config;

    @Test
    @Disabled
    void test() throws InterruptedException {
        int size = 10;
        CountDownLatch countDownLatch = new CountDownLatch(size);

        for (int i = 0; i < 30; i++) {
            log.warn("aaa:[{}]", environment.getProperty("aaa"));
            log.warn("aaa:[{}]", aaa);
            log.warn("bbb:[{}]", bbb);
            log.warn("config.getBbb():[{}]", config.getBbb());
            Thread.sleep(1000);
            System.out.println("--------");
        }
        countDownLatch.await();
    }
}
