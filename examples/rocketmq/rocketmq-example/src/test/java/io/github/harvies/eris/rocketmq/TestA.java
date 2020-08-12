package io.github.harvies.eris.rocketmq;

import org.apache.rocketmq.common.message.MessageDecoder;
import org.apache.rocketmq.common.message.MessageId;
import org.junit.Test;

import java.net.UnknownHostException;

public class TestA {
    @Test
    public void test() throws UnknownHostException {
        //        MessageId messageId = MessageDecoder.decodeMessageId("0A0A64265A4818B4AAC2599F616F0009");
        MessageId messageId = MessageDecoder.decodeMessageId("AC12000600010A1CDC6D59D17CBC001A");
        System.err.println(messageId);
    }
}
