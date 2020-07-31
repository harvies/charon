package io.github.harvies.charon.notify;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import javax.annotation.Resource;

public class CharonNotifyServiceSpringBootTest extends BaseTest {

    @Resource
    private NotifyService notifyService;

    @Test
    public void test() {
        boolean send = notifyService.send("通知-测试标题", "通知-测试内容");
        Assertions.assertTrue(send);
    }
}
