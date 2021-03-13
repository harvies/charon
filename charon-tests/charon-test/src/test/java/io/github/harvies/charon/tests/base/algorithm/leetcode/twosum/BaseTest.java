package io.github.harvies.charon.tests.base.algorithm.leetcode.twosum;

import java.util.HashMap;
import java.util.Map;

public class BaseTest {
    
    protected static final Map<int[], Integer> argMap = new HashMap<>(5);

    static {
        argMap.put(new int[]{3, 2, 4}, 6);
        argMap.put(new int[]{2, 7, 11, 15}, 9);
        argMap.put(new int[]{2, 3, 5, 7}, 8);
    }

}
