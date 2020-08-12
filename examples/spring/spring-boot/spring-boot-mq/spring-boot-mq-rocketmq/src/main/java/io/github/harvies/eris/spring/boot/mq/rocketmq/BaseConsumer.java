package io.github.harvies.eris.spring.boot.mq.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author harvies
 */
public class BaseConsumer implements RocketMQPushConsumerLifecycleListener {
    @Value("${rocketmq.vipChannelEnabled}")
    protected boolean vipChannelEnabled;

    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {
        consumer.setVipChannelEnabled(vipChannelEnabled);
    }
}
