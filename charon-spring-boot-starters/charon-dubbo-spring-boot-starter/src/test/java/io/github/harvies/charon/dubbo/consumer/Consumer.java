package io.github.harvies.charon.dubbo.consumer;

import io.github.harvies.charon.dubbo.BaseTest;
import io.github.harvies.charon.dubbo.api.EchoService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Consumer extends BaseTest {

    @DubboReference(timeout = 1000, injvm = false)
    private EchoService echoService;

    @Test
    void test() {
        assertEquals(echoService.echo("hello"), "hello");
    }
}
