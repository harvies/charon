1.发送消息的流程
> 1.producer询问nameserver，获取topic对应的路由信息org.apache.rocketmq.client.impl.MQClientAPIImpl.getDefaultTopicRouteInfoFromNameServer
> 2.选取要发送的队列 org.apache.rocketmq.client.impl.producer.DefaultMQProducerImpl.selectOneMessageQueue
> 3.最后往broker发送消息 org.apache.rocketmq.client.impl.MQClientAPIImpl.sendMessage(java.lang.String, java.lang.String, org.apache.rocketmq.common.message.Message, org.apache.rocketmq.common.protocol.header.SendMessageRequestHeader, long, org.apache.rocketmq.client.impl.CommunicationMode, org.apache.rocketmq.client.hook.SendMessageContext, org.apache.rocketmq.client.impl.producer.DefaultMQProducerImpl)
