package io.github.harvies.charon.notify;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import javax.annotation.Resource;

public class CharonNotifyProviderSpringBootTest extends BaseTest {

    @Resource
    private NotifyProvider notifyProvider;

    @Test
    public void test() {
        boolean send = notifyProvider.send("通知-测试标题", "通知-测试内容");
        Assertions.assertTrue(send);
    }
}
