package io.github.harvies.eris.spring.boot.mq.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DemoApplicationTests {
    private final static BasicThreadFactory threadFactory = new BasicThreadFactory.Builder().namingPattern("es").build();
    private final static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10000), threadFactory);


    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Test
    public void syncSend() throws InterruptedException {
        //发送1000条消息
        int size = 100;
        CountDownLatch countDownLatch = new CountDownLatch(size);
        for (int i = 0; i < size; i++) {
            //随机1000个字符
            threadPoolExecutor.execute(() -> {
                String randomGraph = RandomStringUtils.randomGraph(1000);
                SendResult sendResult = rocketMQTemplate.syncSend("test-topic:create", randomGraph);
                countDownLatch.countDown();
                log.warn("sendResult:{}", sendResult);
            });

        }
        countDownLatch.await();
        log.warn("asyncSend all end");
    }

    @Test
    public void asyncSend() throws InterruptedException {
        //发送1000条消息
        int size = 10000;
        CountDownLatch countDownLatch = new CountDownLatch(size);
        for (int i = 0; i < size; i++) {
            //随机1000个字符
            threadPoolExecutor.execute(() -> {
                String randomGraph = RandomStringUtils.randomGraph(1000);
                rocketMQTemplate.asyncSend("test-topic:create", randomGraph, new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        log.warn("asyncSend success: string:{} result:{}", randomGraph, sendResult);
                        countDownLatch.countDown();
                    }

                    @Override
                    public void onException(Throwable throwable) {
                        log.warn("asyncSend onException: string:{}", randomGraph, throwable);
                        countDownLatch.countDown();
                    }
                });
            });

        }
        countDownLatch.await();
        log.warn("asyncSend all end");
    }

    @Test
    public void scheduledMessageSend() throws InterruptedException {
        //发送1000条消息
        int size = 1000;
        CountDownLatch countDownLatch = new CountDownLatch(size);
        for (int i = 0; i < size; i++) {
            //随机1000个字符
            String randomGraph = RandomStringUtils.randomGraph(1000);

            Message<String> message = MessageBuilder.withPayload(randomGraph).build();
            /**
             *延迟级别 essageDelayLevel=1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
             * https://blog.csdn.net/xiaoxiaoxuanao/article/details/53433220
             */
            rocketMQTemplate.asyncSend("test-topic:create", message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    log.warn("scheduledMessageSend success: string:{} result:{}", randomGraph, sendResult);
                    countDownLatch.countDown();
                }

                @Override
                public void onException(Throwable throwable) {
                    log.warn("scheduledMessageSend onException: string:{}", randomGraph, throwable);
                    countDownLatch.countDown();
                }

            }, 3000, 4);
        }
        countDownLatch.await();
        log.warn("scheduledMessageSend all end");
    }

    /**
     * 按消息hash分发到不同队列
     *
     * @throws InterruptedException
     */
    @Test
    public void syncSendOrderly() throws InterruptedException {
        //发送1000条消息
        int size = 100;
        CountDownLatch countDownLatch = new CountDownLatch(size);
        for (int i = 0; i < size; i++) {
            //随机1000个字符
            threadPoolExecutor.execute(() -> {
                String randomNumeric = RandomStringUtils.randomNumeric(5);
                System.err.println("hashKey:" + randomNumeric);
                SendResult sendResult = rocketMQTemplate.syncSendOrderly("test-topic:create", randomNumeric, "800599963929");
                countDownLatch.countDown();
                log.warn("sendResult:{}", sendResult);
            });

        }
        countDownLatch.await();
        log.warn("asyncSend all end");
    }

}
