package io.github.harvies.charon.rocketmq.producer;

import io.github.harvies.charon.rocketmq.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Slf4j
class SyncProducerTest extends BaseTest {
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Test
    void syncSend() {
        SendResult sendResult = rocketMQTemplate.syncSend("TopicTest:TagA", "Hello RocketMQ");
        log.info("sendResult:[{}]", sendResult);
        assertThat(sendResult.getSendStatus().name(), is(SendStatus.SEND_OK.name()));
    }

    @Test
    void asyncSend() {
        String randomGraph = RandomStringUtils.randomGraph(1000);
        rocketMQTemplate.asyncSend("TopicTest:TagA", randomGraph, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.warn("asyncSend success: string:{} result:{}", randomGraph, sendResult);
            }

            @Override
            public void onException(Throwable throwable) {
                log.warn("asyncSend onException: string:{}", randomGraph, throwable);
            }
        });
    }

    @Test
    void scheduledMessageSend() {
        //随机1000个字符
        String randomGraph = RandomStringUtils.randomGraph(1000);

        Message<String> message = MessageBuilder.withPayload(randomGraph).build();
        /**
         *延迟级别 essageDelayLevel=1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
         * https://blog.csdn.net/xiaoxiaoxuanao/article/details/53433220
         */
        rocketMQTemplate.asyncSend("TopicTest:TagA", message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.warn("scheduledMessageSend success: string:{} result:{}", randomGraph, sendResult);
            }

            @Override
            public void onException(Throwable throwable) {
                log.warn("scheduledMessageSend onException: string:{}", randomGraph, throwable);
            }

        }, 3000, 4);
    }


    /**
     * 按消息hash分发到不同队列
     *
     */
    @Test
    void syncSendOrderly() {
        String randomNumeric = RandomStringUtils.randomNumeric(5);
        System.err.println("hashKey:" + randomNumeric);
        SendResult sendResult = rocketMQTemplate.syncSendOrderly("TopicTest:TagA", randomNumeric, "800599963929");
        log.warn("sendResult:{}", sendResult);
    }

}
