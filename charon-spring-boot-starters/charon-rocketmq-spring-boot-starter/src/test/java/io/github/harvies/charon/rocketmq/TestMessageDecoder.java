package io.github.harvies.charon.rocketmq;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.common.message.MessageDecoder;
import org.apache.rocketmq.common.message.MessageId;
import org.junit.jupiter.api.Test;

import java.net.UnknownHostException;

public class TestMessageDecoder {
    @Test
    public void decodeMessageId() throws UnknownHostException {
        MessageId messageId = MessageDecoder.decodeMessageId("0A906A5C00002A9F000000000039BD4C");
        System.err.println(JSON.toJSONString(messageId));
    }
}
