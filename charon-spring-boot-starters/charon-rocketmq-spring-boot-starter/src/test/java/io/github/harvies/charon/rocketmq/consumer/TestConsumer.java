package io.github.harvies.charon.rocketmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RocketMQMessageListener(nameServer = "${charon.rocketmq.namesrvAddr}", topic = "TopicTest", selectorExpression = "TagA", consumerGroup = "charon_consumer")
public class TestConsumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        log.info("######## user_consumer received: [{}]", message);
    }
}
