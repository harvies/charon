package io.github.harvies.charon.apollo;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

public class ApolloTest extends BaseTest {
    @Value("${test.property}")
    private String property;

    @Test
    public void test() {
        Assert.assertEquals("test", property);
    }
}
