package io.github.harvies.charon.jdbc;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingValue;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public final class UserComplexKeysShardingAlgorithm implements ComplexKeysShardingAlgorithm<Integer> {

    @Override
    public Collection<String> doSharding(final Collection<String> availableTargetNames, final ComplexKeysShardingValue<Integer> shardingValue) {
        Map<String, Collection<Integer>> columnNameAndShardingValuesMap = shardingValue.getColumnNameAndShardingValuesMap();
        Collection<Integer> id = columnNameAndShardingValuesMap.get("id");
        Collection<Integer> username = columnNameAndShardingValuesMap.get("username");
        Collection<Integer> mobile = columnNameAndShardingValuesMap.get("mobile");

        int hashCode = 0;
        if (CollectionUtils.isNotEmpty(id)) {
            hashCode = id.hashCode();
        } else if (CollectionUtils.isNotEmpty(username)) {
            hashCode = username.hashCode();
        } else if (CollectionUtils.isNotEmpty(mobile)) {
            hashCode = mobile.hashCode();
        }
        int suffix = Math.abs(hashCode) % availableTargetNames.size();
        return availableTargetNames.stream().filter(s -> s.contains(String.valueOf(suffix))).collect(Collectors.toList());
    }
}