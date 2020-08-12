package io.github.harvies.eris.base.hystrix.collapse;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;

import java.util.List;
import java.util.Map;

/**
 * @author harvies
 */
@Slf4j
public class ItemService {
    public static Map<Long, Item> itemMap = Maps.newHashMap();

    static {
        for (int i = 0; i < 10000; i++) {
            Long itemId = (long) i;
            itemMap.put(itemId, Item.builder().id(itemId).name("name" + i).build());
        }
    }

    public Item get(Long itemId) {
        log.warn("get: itemId:{}", itemId);
        try {
            Thread.sleep(RandomUtils.nextInt(0, 1200));
        } catch (InterruptedException e) {
        }
        return itemMap.get(itemId);
    }

    public List<Item> batchGet(List<Long> itemIdList) {
        log.warn("batchGet: itemIdList:{}", itemIdList);
        try {
            Thread.sleep(RandomUtils.nextInt(10, 12));
        } catch (InterruptedException e) {
        }
        List<Item> list = Lists.newArrayList();
        for (Long itemId :
                itemIdList) {
            list.add(itemMap.get(itemId));
        }
        log.warn("batchGet result: itemIdList:{} result:{}", itemIdList, list);
        return list;
    }


}
