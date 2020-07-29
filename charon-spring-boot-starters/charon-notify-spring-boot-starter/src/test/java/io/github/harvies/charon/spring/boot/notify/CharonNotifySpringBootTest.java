package io.github.harvies.charon.spring.boot.notify;

import io.github.harvies.charon.notify.Notify;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;

public class CharonNotifySpringBootTest extends BaseTest {

    @Resource
    private Notify notify;

    @Test
    public void test() {
        boolean send = notify.send("报警-测试标题", "报警-测试内容");
        Assertions.assertTrue(send);
    }
}
