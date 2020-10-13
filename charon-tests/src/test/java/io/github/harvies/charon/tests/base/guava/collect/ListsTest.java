package io.github.harvies.charon.tests.base.guava.collect;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @author harvies
 */
public class ListsTest {
    @Test
    public void testNewArrayList() {
        ArrayList<String> list = Lists.newArrayList("alpha", "beta", "gamma");
        System.out.println(list);
    }
}
