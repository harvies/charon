package io.github.harvies.charon.util;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class StringUtilsTest {

    @Test
    void unicodeEncode() {
        String encode = StringUtils.unicodeEncode("测试");
        assertThat(encode, is("\\u6D4B\\u8BD5"));
    }

    @Test
    void unicodeDecode() {
        String decode = StringUtils.unicodeDecode("\\u6D4B\\u8BD5");
        assertThat(decode, is("测试"));
    }
}