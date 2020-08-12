package io.github.harvies.eris.rocketmq.filter;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * 消费消息
 *
 * @author harvies
 */
public class UserPropertyConsumer {

    public static void main(String[] args) throws MQClientException {

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("please_rename_unique_group_name_4");
        consumer.setNamesrvAddr("localhost:9876");
        // 只有订阅的消息有这个属性a, a >=0 and a <= 3
        /**
         * 需要broker设置enablePropertyFilter=true
         * https://github.com/apache/rocketmq/issues/310
         * https://github.com/apache/rocketmq/issues/455
         */
        consumer.subscribe("TopicTest", MessageSelector.bySql("a between 0 and 3"));
        /**
         * TAG过滤器其实是属性过滤器的特例(属性为TAGS)
         */
        //consumer.subscribe("TopicTest", MessageSelector.bySql("TAGS in ('TAGA')"));
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), new String(msgs.get(0).getBody()));
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
    }
}
