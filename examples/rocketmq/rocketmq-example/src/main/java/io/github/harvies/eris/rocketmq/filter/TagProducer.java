package io.github.harvies.eris.rocketmq.filter;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * 在大多数情况下，TAG是一个简单而有用的设计，其可以来选择您想要的消息。
 *
 * @author harvies
 */
public class TagProducer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();
        //一个消息只能有一个TAG
        String[] tagArray = {"TAGA", "TAGB", "TAGC"};

        for (String tag :
            tagArray) {
            Message message = new Message("TopicTest", tag,
                ("Hello RocketMQ " + tag).getBytes(RemotingHelper.DEFAULT_CHARSET)
            );
            System.out.println(producer.send(message));
        }


        producer.shutdown();
    }
}
