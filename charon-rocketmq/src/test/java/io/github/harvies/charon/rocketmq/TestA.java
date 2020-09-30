package io.github.harvies.charon.rocketmq;

import org.apache.rocketmq.common.message.MessageDecoder;
import org.apache.rocketmq.common.message.MessageId;
import org.junit.jupiter.api.Test;

import java.net.UnknownHostException;

public class TestA {
    @Test
    public void test() throws UnknownHostException {
        MessageId messageId = MessageDecoder.decodeMessageId("0A906A5C00002A9F000000000039BD4C");
        System.err.println(messageId);
    }
}
