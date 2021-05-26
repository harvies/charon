package io.github.harvies.charon.util;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class Base64UtilsTest {

    @Test
    void encode() {
        String str = "hello";
        String encode = Base64Utils.encode(str);
        String decode = Base64Utils.decode(encode);
        assertThat(str, is(decode));
    }
}