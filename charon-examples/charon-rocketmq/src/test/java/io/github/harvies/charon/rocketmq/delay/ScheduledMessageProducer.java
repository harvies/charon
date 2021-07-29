package io.github.harvies.charon.rocketmq.delay;

import io.github.harvies.charon.rocketmq.Constants;
import io.github.harvies.charon.util.PropertiesUtils;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.CountDownLatch;

/**
 * @author harvies
 */
public class ScheduledMessageProducer {
    public static void main(String[] args) throws Exception {
        // 实例化一个生产者来产生延时消息
        DefaultMQProducer producer = new DefaultMQProducer("ExampleProducerGroup");
        producer.setNamesrvAddr(PropertiesUtils.getDefaultProperty(Constants.NAME_SRV_ADDR));
        // 启动生产者
        producer.start();
        int totalMessagesToSend = 100;
        CountDownLatch countDownLatch = new CountDownLatch(totalMessagesToSend);
        for (int i = 0; i < totalMessagesToSend; i++) {
            Message message = new Message("TestTopic", ("Hello scheduled message " + i).getBytes());
            // 设置延时等级3,这个消息将在10s之后发送(现在只支持固定的几个时间,详看delayTimeLevel)
            message.setDelayTimeLevel(3);
            // 发送消息
            try {
                producer.send(message);
            } finally {
                countDownLatch.countDown();
            }
        }
        countDownLatch.await();
        // 关闭生产者
        producer.shutdown();
    }
}
