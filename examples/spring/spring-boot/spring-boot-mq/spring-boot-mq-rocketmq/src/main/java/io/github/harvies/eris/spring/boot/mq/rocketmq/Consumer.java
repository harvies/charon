package io.github.harvies.eris.spring.boot.mq.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * selectorExpression 填tag，因为默认selectorType=tag
 *
 * @author harvies
 */
@Service
@Slf4j
@RocketMQMessageListener(topic = "test-topic", selectorExpression = "create", consumerGroup = "test_create")
public class Consumer extends BaseConsumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        log.warn("onMessage:{}", message);
    }
}
