package io.github.harvies.charon.rocketmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Slf4j
@RocketMQMessageListener(nameServer = "${charon.rocketmq.namesrvAddr}", topic = "TopicTest", selectorExpression = "TagA", consumerGroup = "charon_consumer")
public class UserConsumer implements RocketMQListener<String> {

    @PostConstruct
    public void init() {
        log.info("init");
    }

    @Override
    public void onMessage(String message) {
        System.out.printf("######## user_consumer received: %s ; \n", message);
    }
}