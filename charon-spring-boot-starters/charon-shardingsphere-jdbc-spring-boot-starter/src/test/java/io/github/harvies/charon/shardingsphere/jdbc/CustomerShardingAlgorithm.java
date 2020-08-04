package io.github.harvies.charon.shardingsphere.jdbc;

import com.google.common.base.Splitter;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;
import java.util.List;

/**
 * @author harvies
 */
public class CustomerShardingAlgorithm implements PreciseShardingAlgorithm<String> {

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<String> shardingValue) {
        for (String availableTargetName :
            availableTargetNames) {
            List<String> strings = Splitter.on('_').splitToList(shardingValue.getValue());
            if (availableTargetName.endsWith(strings.get(2))) {
                return availableTargetName;
            }
        }
        return null;
    }
}
