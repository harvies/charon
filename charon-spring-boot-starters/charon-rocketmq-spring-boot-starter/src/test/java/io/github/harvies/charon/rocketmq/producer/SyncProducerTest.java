package io.github.harvies.charon.rocketmq.producer;

import io.github.harvies.charon.rocketmq.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Slf4j
public class SyncProducerTest extends BaseTest {
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Test
    public void syncSend() {
        SendResult sendResult = rocketMQTemplate.syncSend("TopicTest:TagA", "Hello RocketMQ");
        log.info("sendResult:[{}]", sendResult);
        assertThat(sendResult.getSendStatus().name(), is(SendStatus.SEND_OK.name()));
    }
}
