package io.github.harvies.charon.canal;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.protocol.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

@Component
@Slf4j
public class CanalRunner {
    @PostConstruct
    public void init() {
        Map<String, DestinationContext> canalConnectorMap = DestinationContext.destinationContextMap;
        for (DestinationContext destinationContext : canalConnectorMap.values()) {
            test(destinationContext);
        }
    }

    public void test(DestinationContext destinationContext) {
        CanalConnector connector = destinationContext.getCanalConnector();
        int batchSize = 1000;
        int emptyCount = 0;
        try {
            connector.connect();
            connector.subscribe(destinationContext.getFilter());
            connector.rollback();
            int totalEmptyCount = 12000;
            while (emptyCount < totalEmptyCount) {
                Message message = connector.getWithoutAck(batchSize); // 获取指定数量的数据
                long batchId = message.getId();
                int size = message.getEntries().size();
                if (batchId == -1 || size == 0) {
                    emptyCount++;
                    System.out.println("empty count : " + emptyCount);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                } else {
                    emptyCount = 0;
                    log.info("message batchId=[{}],size [{}] message:[{}]", batchId, size, message);
                }

                connector.ack(batchId); // 提交确认
                // connector.rollback(batchId); // 处理失败, 回滚数据
            }

            System.out.println("empty too many times, exit");
        } finally {
            connector.disconnect();
        }
    }
}
