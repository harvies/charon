package io.github.harvies.charon.tests.base.lombok;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author harvies
 */
@Slf4j
public class LombokTest {
    @Test
    public void testNonNull() {
        User user = new User("aaa");
        try {
            user.setUsername(null);
        } catch (Exception e) {
            log.warn("e", e);
        }
    }
}
