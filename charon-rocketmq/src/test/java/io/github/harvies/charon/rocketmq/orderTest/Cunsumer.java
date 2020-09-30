package io.github.harvies.charon.rocketmq.orderTest;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * 消费者
 */
public class Cunsumer {
    public static void main(String[] args) throws MQClientException {
        //创建消费者实例
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumerGroup1");
        //设置nameServer地址
        consumer.setNamesrvAddr("127.0.0.1:9876");
        consumer.subscribe("orderTopic1","*");
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                System.out.printf("NEW MESSAGE:%s%n",msgs);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
        System.out.printf("Consumer Started.%n");
    }

}
