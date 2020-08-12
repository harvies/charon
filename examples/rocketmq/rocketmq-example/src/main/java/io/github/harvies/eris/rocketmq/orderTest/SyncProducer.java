package io.github.harvies.eris.rocketmq.orderTest;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * 同步发送消息
 */
public class SyncProducer {
    public static void main(String[] args) {
        try {
            //实例化消息生产者
            DefaultMQProducer mqProducer = new DefaultMQProducer("order_test_1");
            //设置nameServer
            mqProducer.setNamesrvAddr("localhost:9876");
            //启动生产者实例
            mqProducer.start();
            //装载消息
            for (int i=0;i<5;i++){
                Message message = new Message("orderTopic1","Tag"+i,("order"+i).getBytes());
                message.setDelayTimeLevel(3);
                //发送消息
                SendResult send = mqProducer.send(message);
                System.out.printf("%s%n",send);
            }
            mqProducer.shutdown();
            //关闭连接
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        }

    }
}
