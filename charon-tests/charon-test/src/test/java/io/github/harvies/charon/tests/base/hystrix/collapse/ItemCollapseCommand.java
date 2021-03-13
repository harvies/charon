package io.github.harvies.charon.tests.base.hystrix.collapse;

import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCollapserKey;
import com.netflix.hystrix.HystrixCollapserProperties;
import com.netflix.hystrix.HystrixCommand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author harvies
 */
public class ItemCollapseCommand extends HystrixCollapser<List<Item>, Item, Long> {

    private ItemService itemService;
    private Long itemId;

    public ItemCollapseCommand(ItemService itemService, Long itemId) {
        super(Setter
                .withCollapserKey(HystrixCollapserKey.Factory.asKey("itemCollapseCommand"))
                .andScope(Scope.GLOBAL)
                .andCollapserPropertiesDefaults(
                        HystrixCollapserProperties.Setter()
                                .withTimerDelayInMilliseconds(100)
                                .withMaxRequestsInBatch(5)
                ));
        this.itemService = itemService;
        this.itemId = itemId;
    }

    @Override
    public Long getRequestArgument() {
        return itemId;
    }

    @Override
    protected HystrixCommand<List<Item>> createCommand(Collection<CollapsedRequest<Item, Long>> collapsedRequests) {
        List<Long> itemIdList = new ArrayList<>(collapsedRequests.size());
        itemIdList.addAll(collapsedRequests.stream().map(CollapsedRequest::getArgument).collect(Collectors.toList()));
        return new ItemBatchCommand(itemService, itemIdList);
    }

    @Override
    protected void mapResponseToRequests(List<Item> batchResponse, Collection<CollapsedRequest<Item, Long>> collapsedRequests) {
        int count = 0;
        for (CollapsedRequest<Item, Long> collapsedRequest : collapsedRequests) {
            Item item = batchResponse.get(count++);
            collapsedRequest.setResponse(item);
        }
    }
}
