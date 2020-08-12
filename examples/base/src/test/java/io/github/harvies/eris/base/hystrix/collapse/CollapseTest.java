package io.github.harvies.eris.base.hystrix.collapse;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.RateLimiter;
import io.github.harvies.eris.base.hystrix.collapse.annotations.Merge;
import io.github.harvies.eris.base.hystrix.collapse.annotations.NotMerge;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.Future;

@Slf4j
public class CollapseTest {
    private static RateLimiter rateLimiter = RateLimiter.create(100);

    @Test
    @Merge
    public void test() {
        ItemService itemService = new ItemService();
        for (int j = 0; j < 2; j++) {
            List<Future<Item>> futureList = Lists.newArrayList();
//            HystrixRequestContext hystrixRequestContext = null;
            try {
                int size = 10;
                //Scope.GLOBAL 不需要设置
//                hystrixRequestContext = HystrixRequestContext.initializeContext();
                for (int i = 0; i < size; i++) {
                    rateLimiter.acquire();
                    log.warn("get item itemId:{}", i);
                    ItemCollapseCommand itemCollapseCommand = new ItemCollapseCommand(itemService, (long) i);
                    Future<Item> queue = itemCollapseCommand.queue();
                    futureList.add(queue);
                }
                futureList.forEach(itemFuture -> {
                    try {
                        log.warn("get item result:{}", itemFuture.get());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

            } finally {
//                if (hystrixRequestContext != null) {
//                    hystrixRequestContext.shutdown();
//                }
            }
        }
    }


    @Test
    @NotMerge
    public void origin() {
        int size = 10;
        ItemService itemService = new ItemService();
        for (int i = 0; i < size; i++) {
            log.warn("result:{}", itemService.get((long) i));
        }
    }

}
