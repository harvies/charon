package io.github.harvies.charon.rocketmq.base;

import io.github.harvies.charon.rocketmq.Constants;
import io.github.harvies.charon.util.PropertiesUtils;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * 3、单向发送消息
 * 这种方式主要用在不特别关心发送结果的场景，例如日志发送。
 *
 * @author harvies
 */
public class OnewayProducer {
    public static void main(String[] args) throws Exception {
        // 实例化消息生产者Producer
        DefaultMQProducer producer = new DefaultMQProducer("group_oneway_producer");
        // 设置NameServer的地址
        producer.setNamesrvAddr(PropertiesUtils.getDefaultProperty(Constants.NAME_SRV_ADDR));
        // 启动Producer实例
        producer.start();
        for (int i = 0; i < 100; i++) {
            // 创建消息，并指定Topic，Tag和消息体
            Message msg = new Message("TopicTest" /* Topic */,
                    "TagA" /* Tag */,
                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            // 发送单向消息，没有任何返回结果
            producer.sendOneway(msg);

        }
        // 如果不再发送消息，关闭Producer实例。
        producer.shutdown();
    }
}
