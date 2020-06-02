package io.github.harvies.charon.json;

import org.junit.Assert;
import org.junit.Test;

public class ObjectTest {
    @Test
    public void serialization() {
        // Serialization
        BagOfPrimitives obj = new BagOfPrimitives();
        String json = JsonUtils.toJSONString(obj);
        // ==> json is {"value1":1,"value2":"abc"}
        Assert.assertEquals("{\"value1\":1,\"value2\":\"abc\"}", json);
    }

    @Test
    public void deserialization() {
        // Deserialization
        BagOfPrimitives obj2 = JsonUtils.parseObject("{\"value1\":1,\"value2\":\"abc\"}", BagOfPrimitives.class);
        // ==> obj2 is just like obj
        Assert.assertEquals(obj2.value1, 1);
        Assert.assertEquals(obj2.value2, "abc");
    }

    class BagOfPrimitives {
        private int value1 = 1;
        private String value2 = "abc";
        private transient int value3 = 3;

        BagOfPrimitives() {
            // no-args constructor
        }
    }
}
