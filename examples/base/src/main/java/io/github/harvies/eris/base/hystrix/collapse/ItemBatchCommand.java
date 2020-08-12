package io.github.harvies.eris.base.hystrix.collapse;

import com.google.common.collect.Lists;
import com.netflix.hystrix.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;


/**
 * @author harvies
 */
@Slf4j
public class ItemBatchCommand extends HystrixCommand<List<Item>> {

    private ItemService itemService;
    private List<Long> itemIdList;

    public ItemBatchCommand(ItemService itemService, List<Long> itemIdList) {
        super(Setter
                //线程名等会显示
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("itemBatchCommandGroup"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("itemBatchCommand"))
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                        //默认10
                        .withCoreSize(100)
                        //默认10
                        .withMaximumSize(100)
                )
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        //批量方法调用超时时间
                        .withExecutionTimeoutInMilliseconds(10000)
                )
        );
        this.itemService = itemService;
        this.itemIdList = itemIdList;
    }

    @Override
    protected List<Item> run() {
        return itemService.batchGet(itemIdList);
    }

    @Override
    protected List<Item> getFallback() {
        log.warn("ItemBatchCommand timeout itemIdList:{}", itemIdList);
        /**
         * 超时返回空对象,实际场景可以从数据库查询或者在没短路的情况下再重试
         *  if (!isCircuitBreakerOpen()) {
         *             //此处需要command包装
         *             return xxx();
         *         }
         */
        ArrayList<Item> list = Lists.newArrayListWithCapacity(itemIdList.size());
        itemIdList.forEach(itemId -> list.add(null));
        return list;
    }
}
